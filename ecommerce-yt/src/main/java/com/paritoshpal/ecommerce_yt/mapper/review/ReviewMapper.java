package com.paritoshpal.ecommerce_yt.mapper.review;

import com.paritoshpal.ecommerce_yt.dto.review.ReviewDTO;
import com.paritoshpal.ecommerce_yt.dto.review.ReviewRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.review.ReviewResponseDTO;
import com.paritoshpal.ecommerce_yt.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source ="user.id" ,target = "userId")
    ReviewDTO toReviewDTO(Review review);

    Review toReview(ReviewRequestDTO reviewRequestDTO);

    @Mapping(source = "user.id" ,target = "userId")
    @Mapping(source = "product.id" ,target = "productId")
    ReviewResponseDTO toReviewResponseDTO(Review review);


}
