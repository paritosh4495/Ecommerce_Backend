package com.paritoshpal.ecommerce_yt.service.orderItem;

import com.paritoshpal.ecommerce_yt.dto.orderItem.OrderItemDTO;
import com.paritoshpal.ecommerce_yt.exception.OrderItemNotFoundException;
import com.paritoshpal.ecommerce_yt.mapper.orderItem.OrderItemMapper;
import com.paritoshpal.ecommerce_yt.model.OrderItem;
import com.paritoshpal.ecommerce_yt.repository.OrderItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(Long orderItemId, OrderItem orderItem) {

        OrderItem existingOrderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(()-> new OrderItemNotFoundException("Order item not found"));

        existingOrderItem.setProduct(orderItem.getProduct());
        existingOrderItem.setQuantity(orderItem.getQuantity());
        existingOrderItem.setPrice(orderItem.getPrice());
        existingOrderItem.setDiscountedPrice(orderItem.getDiscountedPrice());
        return orderItemRepository.save(existingOrderItem);

    }

    @Override
    public String deleteOrderItem(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(()-> new OrderItemNotFoundException("Order item not found"));
        orderItemRepository.delete(orderItem);
        return "Order item deleted with id: " + orderItemId;
    }

    @Override
    public OrderItemDTO getOrderItemDTO(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(()-> new OrderItemNotFoundException("Order item not found"));
        return orderItemMapper.toOrderItemDTO(orderItem);
    }
}
