package com.paritoshpal.ecommerce_yt.dto.rating;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RatingRequestDTO {

    @NotNull
    @NotBlank
    private double rating;

    @NotNull
    private Long productId;
}
