package com.paritoshpal.ecommerce_yt.mapper.review;

import com.paritoshpal.ecommerce_yt.dto.review.ReviewDTO;
import com.paritoshpal.ecommerce_yt.model.Review;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-19T16:09:14+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewDTO toReviewDTO(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setReview( review.getReview() );

        return reviewDTO;
    }

    @Override
    public Review toReview(ReviewDTO reviewDTO) {
        if ( reviewDTO == null ) {
            return null;
        }

        Review review = new Review();

        review.setReview( reviewDTO.getReview() );

        return review;
    }
}
