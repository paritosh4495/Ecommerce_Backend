package com.paritoshpal.ecommerce_yt.service.order;

import com.paritoshpal.ecommerce_yt.dto.order.OrderRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.order.OrderResponseDTO;
import com.paritoshpal.ecommerce_yt.dto.order.OrderSummaryDTO;
import com.paritoshpal.ecommerce_yt.model.Order;
import com.razorpay.RazorpayException;

import java.util.List;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO getOrder(Long orderId);

    List<OrderResponseDTO> usersDetailedOrderHistory(Long userId);

    List<OrderSummaryDTO> usersOrderHistory(Long userId);


    // for ADMIN ONLY
    OrderResponseDTO confirmOrder(Long orderId);

    OrderResponseDTO shipOrder(Long orderId);

    OrderResponseDTO deliverOrder(Long orderId);

    OrderResponseDTO initiateOrderReturn(Long orderId);

    OrderResponseDTO confirmReturnOrder(Long orderId);

    // CAN BE DONE BY BOTH ADMIN AND USER
    OrderResponseDTO cancelOrder(Long orderId);

    List<OrderResponseDTO> getAllOrders();

    String deleteOrder(Long orderId);

}
