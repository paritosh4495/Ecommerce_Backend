package com.paritoshpal.ecommerce_yt.dto.product;

import com.paritoshpal.ecommerce_yt.dto.rating.RatingDTO;
import com.paritoshpal.ecommerce_yt.dto.review.ReviewDTO;
import com.paritoshpal.ecommerce_yt.model.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class ProductResponseDTO {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal discountedPrice;
    private int discountPercentage;
    private int quantity;
    private String brand;
    private String color;
    private String imageUrl;
    private Set<Size> sizes;
    private List<RatingDTO> ratings;
    private List<ReviewDTO> reviews;
    private int numRatings;
    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
