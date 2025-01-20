package com.paritoshpal.ecommerce_yt.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRequestDTO {

    @NotNull
    @NotBlank
    private String review;

    @NotNull
    private Long productId;

}
