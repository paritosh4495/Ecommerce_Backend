package com.paritoshpal.ecommerce_yt.mapper.rating;

import com.paritoshpal.ecommerce_yt.dto.rating.RatingDTO;
import com.paritoshpal.ecommerce_yt.model.Rating;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-19T16:09:14+0530",
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

        ratingDTO.setRating( rating.getRating() );

        return ratingDTO;
    }

    @Override
    public Rating toRating(RatingDTO ratingDTO) {
        if ( ratingDTO == null ) {
            return null;
        }

        Rating rating = new Rating();

        rating.setRating( ratingDTO.getRating() );

        return rating;
    }
}
