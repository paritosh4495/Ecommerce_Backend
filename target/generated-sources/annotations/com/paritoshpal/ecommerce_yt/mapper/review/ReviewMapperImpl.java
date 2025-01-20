package com.paritoshpal.ecommerce_yt.mapper.review;

import com.paritoshpal.ecommerce_yt.dto.review.ReviewDTO;
import com.paritoshpal.ecommerce_yt.dto.review.ReviewRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.review.ReviewResponseDTO;
import com.paritoshpal.ecommerce_yt.model.Product;
import com.paritoshpal.ecommerce_yt.model.Review;
import com.paritoshpal.ecommerce_yt.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-20T12:30:58+0530",
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

        reviewDTO.setUserId( reviewUserId( review ) );
        reviewDTO.setReview( review.getReview() );

        return reviewDTO;
    }

    @Override
    public Review toReview(ReviewRequestDTO reviewRequestDTO) {
        if ( reviewRequestDTO == null ) {
            return null;
        }

        Review review = new Review();

        review.setReview( reviewRequestDTO.getReview() );

        return review;
    }

    @Override
    public ReviewResponseDTO toReviewResponseDTO(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();

        reviewResponseDTO.setUserId( reviewUserId( review ) );
        reviewResponseDTO.setProductId( reviewProductId( review ) );
        reviewResponseDTO.setId( review.getId() );
        reviewResponseDTO.setReview( review.getReview() );
        reviewResponseDTO.setCreatedAt( review.getCreatedAt() );
        reviewResponseDTO.setUpdatedAt( review.getUpdatedAt() );

        return reviewResponseDTO;
    }

    private Long reviewUserId(Review review) {
        User user = review.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private Long reviewProductId(Review review) {
        Product product = review.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }
}
