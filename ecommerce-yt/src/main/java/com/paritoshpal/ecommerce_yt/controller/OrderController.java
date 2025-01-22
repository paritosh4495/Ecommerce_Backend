package com.paritoshpal.ecommerce_yt.controller;

import com.paritoshpal.ecommerce_yt.dto.order.OrderRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.order.OrderResponseDTO;
import com.paritoshpal.ecommerce_yt.dto.order.OrderSummaryDTO;
import com.paritoshpal.ecommerce_yt.response.ApiResponse;
import com.paritoshpal.ecommerce_yt.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO createdOrder = orderService.createOrder(orderRequestDTO);
        ApiResponse<OrderResponseDTO> response = new ApiResponse<>(createdOrder, "Order created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> getOrder(@PathVariable Long orderId) {
        OrderResponseDTO order = orderService.getOrder(orderId);
        ApiResponse<OrderResponseDTO> response = new ApiResponse<>(order, "Order fetched successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/user/{userId}/detailed-history")
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> getUsersDetailedOrderHistory(@PathVariable Long userId) {
        List<OrderResponseDTO> orderHistory = orderService.usersDetailedOrderHistory(userId);
        ApiResponse<List<OrderResponseDTO>> response = new ApiResponse<>(orderHistory, "Order history fetched successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/user/{userId}/summary-history")
    public ResponseEntity<ApiResponse<List<OrderSummaryDTO>>> getUsersOrderHistory(@PathVariable Long userId) {
        List<OrderSummaryDTO> orderHistory = orderService.usersOrderHistory(userId);
        ApiResponse<List<OrderSummaryDTO>> response = new ApiResponse<>(orderHistory, "Order summary fetched successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/place/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> placeOrder(@PathVariable Long orderId) {
        OrderResponseDTO placedOrder = orderService.placeOrder(orderId);
        ApiResponse<OrderResponseDTO> response = new ApiResponse<>(placedOrder, "Order placed successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/confirm/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> confirmOrder(@PathVariable Long orderId) {
        OrderResponseDTO confirmedOrder = orderService.confirmOrder(orderId);
        ApiResponse<OrderResponseDTO> response = new ApiResponse<>(confirmedOrder, "Order confirmed successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ship/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> shipOrder(@PathVariable Long orderId) {
        OrderResponseDTO shippedOrder = orderService.shipOrder(orderId);
        ApiResponse<OrderResponseDTO> response = new ApiResponse<>(shippedOrder, "Order shipped successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/deliver/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> deliverOrder(@PathVariable Long orderId) {
        OrderResponseDTO deliveredOrder = orderService.deliverOrder(orderId);
        ApiResponse<OrderResponseDTO> response = new ApiResponse<>(deliveredOrder, "Order delivered successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/return/initiate/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> initiateOrderReturn(@PathVariable Long orderId) {
        OrderResponseDTO initiatedReturnOrder = orderService.initiateOrderReturn(orderId);
        ApiResponse<OrderResponseDTO> response = new ApiResponse<>(initiatedReturnOrder, "Order return initiated successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/return/confirm/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> confirmReturnOrder(@PathVariable Long orderId) {
        OrderResponseDTO confirmedReturnOrder = orderService.confirmReturnOrder(orderId);
        ApiResponse<OrderResponseDTO> response = new ApiResponse<>(confirmedReturnOrder, "Order return confirmed successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> cancelOrder(@PathVariable Long orderId) {
        OrderResponseDTO cancelledOrder = orderService.cancelOrder(orderId);
        ApiResponse<OrderResponseDTO> response = new ApiResponse<>(cancelledOrder, "Order cancelled successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        ApiResponse<List<OrderResponseDTO>> response = new ApiResponse<>(orders, "All orders fetched successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse<String>> deleteOrder(@PathVariable Long orderId) {
        String message = orderService.deleteOrder(orderId);
        ApiResponse<String> response = new ApiResponse<>(message, "Order deleted successfully");
        return ResponseEntity.ok(response);
    }
}
