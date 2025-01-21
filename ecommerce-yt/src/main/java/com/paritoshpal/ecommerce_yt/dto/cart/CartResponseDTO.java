package com.paritoshpal.ecommerce_yt.dto.cart;

import com.paritoshpal.ecommerce_yt.dto.cartItem.CartItemResponseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CartResponseDTO {

    private Long id;
    private Long userId;
    private Set<CartItemResponseDTO> cartItems;
    private BigDecimal totalPrice;
    private int totalItems;
    private BigDecimal totalDiscountedPrice;
    private Integer discount;

}
