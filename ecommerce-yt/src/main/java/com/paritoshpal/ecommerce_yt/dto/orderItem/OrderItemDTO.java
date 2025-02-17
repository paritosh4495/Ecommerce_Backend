package com.paritoshpal.ecommerce_yt.dto.orderItem;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {

    private Long productId;
    private String title;
    private String brand;
    private String imageUrl;
    private String size;
    private int quantity;
    private BigDecimal price;
    private BigDecimal discountedPrice;


}
