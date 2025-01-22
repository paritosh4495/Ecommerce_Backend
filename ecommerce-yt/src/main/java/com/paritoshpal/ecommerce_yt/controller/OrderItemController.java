package com.paritoshpal.ecommerce_yt.controller;

import com.paritoshpal.ecommerce_yt.dto.orderItem.OrderItemDTO;
import com.paritoshpal.ecommerce_yt.model.Order;
import com.paritoshpal.ecommerce_yt.model.OrderItem;
import com.paritoshpal.ecommerce_yt.response.ApiResponse;
import com.paritoshpal.ecommerce_yt.service.orderItem.OrderItemService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order-items")
public class OrderItemController {


    private final OrderItemService orderItemService;


    @GetMapping("/{orderItemId}")
    public ResponseEntity<ApiResponse<OrderItemDTO>> getOrderItem(@PathVariable Long orderItemId) {
        OrderItemDTO orderItemDTO = orderItemService.getOrderItemDTO(orderItemId);
        ApiResponse<OrderItemDTO> response = new ApiResponse<>(orderItemDTO, "Order item retrieved successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{orderItemId}")
    public ResponseEntity<ApiResponse<OrderItem>> updateOrderItem(@PathVariable Long orderItemId, @RequestBody OrderItem RorderItem) {
        OrderItem orderItem = orderItemService.updateOrderItem(orderItemId,RorderItem);
        ApiResponse<OrderItem> response = new ApiResponse<>(orderItem, "Order item updated successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<ApiResponse<String>> deleteOrderItem(@PathVariable Long orderItemId) {
        String result = orderItemService.deleteOrderItem(orderItemId);
        ApiResponse<String> response = new ApiResponse<>(result, "Order item deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
