package com.paritoshpal.ecommerce_yt.dto.review;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewResponseDTO {

    private Long id;
    private String review;
    private Long productId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
