package com.paritoshpal.ecommerce_yt.dto.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartRequestDTO {

    @NotNull(message = "Product ID cannot be null")
    private Long productId;

    @NotBlank(message = "size cannot be blank")
    private String size;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    private BigDecimal price;

}
