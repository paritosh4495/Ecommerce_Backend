package com.paritoshpal.ecommerce_yt.service.order;

import com.paritoshpal.ecommerce_yt.dto.order.OrderRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.order.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);

    List<OrderResponseDTO> usersOrderHistory(Long userId);

    OrderResponseDTO placeOrder(Long orderId);

    OrderResponseDTO confirmOrder(Long orderId);

    OrderResponseDTO shipOrder(Long orderId);

    OrderResponseDTO deliverOrder(Long orderId);

    OrderResponseDTO cancelOrder(Long orderId);

    List<OrderResponseDTO> getAllOrders();

    String deleteOrder(Long orderId);
}
