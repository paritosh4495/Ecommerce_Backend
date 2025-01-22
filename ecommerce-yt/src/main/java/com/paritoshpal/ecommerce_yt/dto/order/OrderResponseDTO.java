package com.paritoshpal.ecommerce_yt.dto.order;

import com.paritoshpal.ecommerce_yt.dto.address.AddressDTO;
import com.paritoshpal.ecommerce_yt.dto.address.AddressResponseDTO;
import com.paritoshpal.ecommerce_yt.dto.orderItem.OrderItemDTO;
import com.paritoshpal.ecommerce_yt.enums.OrderStatus;
import com.paritoshpal.ecommerce_yt.model.PaymentDetails;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {

    private Long id;
    private String orderId;
    private List<OrderItemDTO> orderItems;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private AddressResponseDTO address;
    private PaymentDetails paymentDetails;
    private BigDecimal totalPrice;
    private BigDecimal totalDiscountedPrice;
    private Integer discount;
    private OrderStatus orderStatus;
    private int totalItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
