package com.paritoshpal.ecommerce_yt.mapper.rating;

import com.paritoshpal.ecommerce_yt.dto.rating.RatingDTO;
import com.paritoshpal.ecommerce_yt.model.Rating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    RatingDTO toRatingDTO(Rating rating);

    Rating toRating(RatingDTO ratingDTO);

}
