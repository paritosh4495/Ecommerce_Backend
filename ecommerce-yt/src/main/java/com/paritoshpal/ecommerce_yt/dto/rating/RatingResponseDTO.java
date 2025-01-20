package com.paritoshpal.ecommerce_yt.dto.rating;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingResponseDTO {

    private Long id;
    private double rating;
    private Long productId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
