package com.paritoshpal.ecommerce_yt.mapper.rating;

import com.paritoshpal.ecommerce_yt.dto.rating.RatingDTO;
import com.paritoshpal.ecommerce_yt.dto.rating.RatingRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.rating.RatingResponseDTO;
import com.paritoshpal.ecommerce_yt.model.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    @Mapping(source ="user.id" ,target = "userId")
    RatingDTO toRatingDTO(Rating rating);


    Rating toRating(RatingRequestDTO ratingRequestDTO);

    @Mapping(source = "user.id",target = "userId")
    @Mapping(source = "product.id",target = "productId")
    RatingResponseDTO toRatingResponseDTO(Rating rating);




}
