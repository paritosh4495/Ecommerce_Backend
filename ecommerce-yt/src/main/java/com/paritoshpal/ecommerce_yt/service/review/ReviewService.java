package com.paritoshpal.ecommerce_yt.service.review;

import com.paritoshpal.ecommerce_yt.dto.rating.RatingRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.rating.RatingResponseDTO;
import com.paritoshpal.ecommerce_yt.dto.review.ReviewRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.review.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {

    ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO);

    ReviewResponseDTO updateReview(Long reviewId,ReviewRequestDTO reviewRequestDTO);
    ReviewResponseDTO getReview(Long id);

    List<ReviewResponseDTO> getReviewsByUserId(Long userId);

    List<ReviewResponseDTO> getReviewsByProductId(Long productId);

    String deleteReview(Long id);
}
