package com.paritoshpal.ecommerce_yt.mapper.review;

import com.paritoshpal.ecommerce_yt.dto.review.ReviewDTO;
import com.paritoshpal.ecommerce_yt.model.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewDTO toReviewDTO(Review review);

    Review toReview(ReviewDTO reviewDTO);

}
