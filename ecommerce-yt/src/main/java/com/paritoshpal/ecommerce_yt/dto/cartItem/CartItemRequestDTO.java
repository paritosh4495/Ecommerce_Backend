package com.paritoshpal.ecommerce_yt.dto.cartItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemRequestDTO {


    // WE shall take cartid and userid in service layer
    // Fetch User -> then fetch its cart
    // since on on one relationship

    @NotNull(message = "Product ID cannot be null")
    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity=1;

    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal discountedPrice;
}
