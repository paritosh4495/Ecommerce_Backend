package com.paritoshpal.ecommerce_yt.service.order;

import com.paritoshpal.ecommerce_yt.dto.order.OrderRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.order.OrderResponseDTO;
import com.paritoshpal.ecommerce_yt.dto.order.OrderSummaryDTO;
import com.paritoshpal.ecommerce_yt.enums.OrderStatus;
import com.paritoshpal.ecommerce_yt.enums.PaymentStatus;
import com.paritoshpal.ecommerce_yt.exception.CartNotFoundException;
import com.paritoshpal.ecommerce_yt.exception.InsufficientInventoryException;
import com.paritoshpal.ecommerce_yt.exception.OrderNotFoundException;
import com.paritoshpal.ecommerce_yt.exception.UserNotFoundException;
import com.paritoshpal.ecommerce_yt.mapper.order.OrderMapper;
import com.paritoshpal.ecommerce_yt.model.*;
import com.paritoshpal.ecommerce_yt.repository.AddressRepository;
import com.paritoshpal.ecommerce_yt.repository.CartRepository;
import com.paritoshpal.ecommerce_yt.repository.OrderRepository;
import com.paritoshpal.ecommerce_yt.repository.UserRepository;
import com.paritoshpal.ecommerce_yt.security.user.CustomUserDetails;
import com.paritoshpal.ecommerce_yt.service.cart.CartService;
import com.paritoshpal.ecommerce_yt.service.orderItem.OrderItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderItemService orderItemService;
    private final CartRepository cartRepository;
    private final CartService cartService;


    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {

        Long userId = getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));
        // Check if the cart is empty
        if (cart.getCartItems().isEmpty()) {
            throw new IllegalStateException("Cart cannot be empty! Please add items to the cart.");
        }
        orderRequestDTO.getAddress().setUser(user);
        Address address = addressRepository.save(orderRequestDTO.getAddress());
        user.getAddresses().add(address);
        userRepository.save(user);

        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setDeliveryDate(LocalDateTime.now().plusDays(7));
        order.setAddress(orderRequestDTO.getAddress());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setTotalPrice(BigDecimal.ZERO);
        orderRepository.save(order);

        List<OrderItem> orderItems = cart.getCartItems().stream()
                .map(cartItem -> {
                    // Final Inventory Check!
                    Product product = cartItem.getProduct();
                    if(product.getQuantity()<cartItem.getQuantity()){
                        throw new InsufficientInventoryException("Sorry Product Is Out of Stock Try Reducing the quantity for the product : "+product.getTitle()+ " and check again !");
                    }

                    // Reduce inventory
                    product.setQuantity(product.getQuantity() - cartItem.getQuantity());

                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(product);
                    orderItem.setSize(cartItem.getSize());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(cartItem.getPrice());
                    orderItem.setDiscountedPrice(cartItem.getDiscountedPrice());
                    orderItem.setUserId(userId);
                    orderItem.setDeliveryDate(order.getDeliveryDate());
                    return orderItemService.createOrderItem(orderItem);
                }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        updateOrderTotals(order);
        order.getPaymentDetails().setStatus(PaymentStatus.PENDING);
        order.setOrderStatus(OrderStatus.PENDING);

        orderRepository.save(order);
        cartService.clearCart(userId);

        // Explicitly delete cart items from the database



        return orderMapper.toOrderResponseDTO(order);

    }

    @Override
    public OrderResponseDTO getOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException("Order not found"));

        return orderMapper.toOrderResponseDTO(order);

    }

    @Override
    public List<OrderResponseDTO> usersDetailedOrderHistory(Long userId) {

        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(orderMapper::toOrderResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderSummaryDTO> usersOrderHistory(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(orderMapper::toOrderSummaryDTO).collect(Collectors.toList());

    }

    @Override
    public OrderResponseDTO placeOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        Long userId = getCurrentUserId();

        if(order.getUser().getId()!=userId){
            throw new IllegalStateException("You Cannot place someone elses order");
        }
        order.setOrderStatus(OrderStatus.PLACED);
        order.getPaymentDetails().setStatus(PaymentStatus.COMPLETED);
        orderRepository.save(order);
        return orderMapper.toOrderResponseDTO(order);

    }

    @Override
    public OrderResponseDTO confirmOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));


        order.setOrderStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
        return orderMapper.toOrderResponseDTO(order);
    }

    @Override
    public OrderResponseDTO shipOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));


        order.setOrderStatus(OrderStatus.SHIPPED);
        orderRepository.save(order);
        return orderMapper.toOrderResponseDTO(order);
    }

    @Override
    public OrderResponseDTO deliverOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.setOrderStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);
        return orderMapper.toOrderResponseDTO(order);
    }

    @Override
    public OrderResponseDTO initiateOrderReturn(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        Long userId = getCurrentUserId();

        if(order.getUser().getId()!=userId){
            throw new IllegalStateException("You Cannot return someone elses order");
        }

        order.setOrderStatus(OrderStatus.INITIATED_RETURN);
        order.getPaymentDetails().setStatus(PaymentStatus.REFUND_INITIATED);
        orderRepository.save(order);
        return orderMapper.toOrderResponseDTO(order);
    }


    @Override
    public OrderResponseDTO confirmReturnOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        // Restock inventory
        for (OrderItem orderItem : order.getOrderItems()) {
            Product product = orderItem.getProduct();
            product.setQuantity(product.getQuantity() + orderItem.getQuantity());
        }

        order.setOrderStatus(OrderStatus.RETURNED);
        order.getPaymentDetails().setStatus(PaymentStatus.REFUNDED);
        orderRepository.save(order);
        return orderMapper.toOrderResponseDTO(order);
    }

    @Override
    public OrderResponseDTO cancelOrder(Long orderId) {


        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        Long userId = getCurrentUserId();

        if(order.getUser().getId()!=userId || userId==1){
            throw new IllegalStateException("You Cannot Cancel someone else's order");
        }
        if(order.getOrderStatus().equals(OrderStatus.SHIPPED)){
            throw new IllegalArgumentException("Shipped order cannot be cancelled Please Retrun IT");
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        order.getPaymentDetails().setStatus(PaymentStatus.REFUNDED);
        orderRepository.save(order);
        return orderMapper.toOrderResponseDTO(order);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {

        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toOrderResponseDTO).collect(Collectors.toList());

    }

    @Override
    public String deleteOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        orderRepository.delete(order);
        return "Order Deleted Successfully";
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }

    private void updateOrderTotals(Order order) {
        int totalItems = order.getOrderItems().stream().mapToInt(OrderItem::getQuantity).sum();
        BigDecimal totalPrice = order.getOrderItems().stream()
                .map(orderItem -> orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalDiscountedPrice = order.getOrderItems().stream()
                .map(orderItem -> orderItem.getDiscountedPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal discount = totalPrice.subtract(totalDiscountedPrice);
        int discountPercentage = discount.divide(totalPrice, 2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)).intValue();

        order.setTotalItems(totalItems);
        order.setTotalPrice(totalPrice);
        order.setTotalDiscountedPrice(totalDiscountedPrice);
        order.setDiscount(discountPercentage);
    }
}
