package com.paritoshpal.ecommerce_yt.service.rating;

import com.paritoshpal.ecommerce_yt.dto.rating.RatingRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.rating.RatingResponseDTO;

import java.util.List;

public interface RatingService {

    RatingResponseDTO createRating(RatingRequestDTO ratingRequestDTO);

    RatingResponseDTO updateRating(Long ratingId, RatingRequestDTO ratingRequestDTO);

    RatingResponseDTO getRating(Long id);

    List<RatingResponseDTO> getRatingsByUserId(Long userId);

    List<RatingResponseDTO> getRatingsByProductId(Long productId);

    String deleteRating(Long id);
}
