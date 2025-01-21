package com.paritoshpal.ecommerce_yt.service.order;

import com.paritoshpal.ecommerce_yt.dto.order.OrderRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.order.OrderResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        return null;
    }

    @Override
    public List<OrderResponseDTO> usersOrderHistory(Long userId) {
        return List.of();
    }

    @Override
    public OrderResponseDTO placeOrder(Long orderId) {
        return null;
    }

    @Override
    public OrderResponseDTO confirmOrder(Long orderId) {
        return null;
    }

    @Override
    public OrderResponseDTO shipOrder(Long orderId) {
        return null;
    }

    @Override
    public OrderResponseDTO deliverOrder(Long orderId) {
        return null;
    }

    @Override
    public OrderResponseDTO cancelOrder(Long orderId) {
        return null;
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return List.of();
    }

    @Override
    public String deleteOrder(Long orderId) {
        return "";
    }
}
