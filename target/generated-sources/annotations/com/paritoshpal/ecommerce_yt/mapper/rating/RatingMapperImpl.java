package com.paritoshpal.ecommerce_yt.mapper.rating;

import com.paritoshpal.ecommerce_yt.dto.rating.RatingDTO;
import com.paritoshpal.ecommerce_yt.dto.rating.RatingRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.rating.RatingResponseDTO;
import com.paritoshpal.ecommerce_yt.model.Product;
import com.paritoshpal.ecommerce_yt.model.Rating;
import com.paritoshpal.ecommerce_yt.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-29T18:55:06+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class RatingMapperImpl implements RatingMapper {

    @Override
    public RatingDTO toRatingDTO(Rating rating) {
        if ( rating == null ) {
            return null;
        }

        RatingDTO ratingDTO = new RatingDTO();

        ratingDTO.setUserId( ratingUserId( rating ) );
        ratingDTO.setRating( rating.getRating() );

        return ratingDTO;
    }

    @Override
    public Rating toRating(RatingRequestDTO ratingRequestDTO) {
        if ( ratingRequestDTO == null ) {
            return null;
        }

        Rating rating = new Rating();

        rating.setRating( ratingRequestDTO.getRating() );

        return rating;
    }

    @Override
    public RatingResponseDTO toRatingResponseDTO(Rating rating) {
        if ( rating == null ) {
            return null;
        }

        RatingResponseDTO ratingResponseDTO = new RatingResponseDTO();

        ratingResponseDTO.setUserId( ratingUserId( rating ) );
        ratingResponseDTO.setProductId( ratingProductId( rating ) );
        ratingResponseDTO.setId( rating.getId() );
        ratingResponseDTO.setRating( rating.getRating() );
        ratingResponseDTO.setCreatedAt( rating.getCreatedAt() );
        ratingResponseDTO.setUpdatedAt( rating.getUpdatedAt() );

        return ratingResponseDTO;
    }

    private Long ratingUserId(Rating rating) {
        User user = rating.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private Long ratingProductId(Rating rating) {
        Product product = rating.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }
}
