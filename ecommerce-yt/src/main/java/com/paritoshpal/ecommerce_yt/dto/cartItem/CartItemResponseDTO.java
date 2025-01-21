package com.paritoshpal.ecommerce_yt.dto.cartItem;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponseDTO {

    private Long id;
    private Long productId;
    private String size;
    private int quantity;
    private BigDecimal price;
    private BigDecimal discountedPrice;
}
