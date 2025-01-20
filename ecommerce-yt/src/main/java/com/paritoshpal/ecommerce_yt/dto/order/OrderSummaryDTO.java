package com.paritoshpal.ecommerce_yt.dto.order;

import com.paritoshpal.ecommerce_yt.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderSummaryDTO {

    private String orderId;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private BigDecimal totalPrice;
    private OrderStatus orderStatus;

}
