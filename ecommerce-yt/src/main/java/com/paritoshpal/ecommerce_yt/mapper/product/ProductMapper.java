package com.paritoshpal.ecommerce_yt.mapper.product;

import com.paritoshpal.ecommerce_yt.dto.product.ProductRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.product.ProductResponseDTO;
import com.paritoshpal.ecommerce_yt.mapper.rating.RatingMapper;
import com.paritoshpal.ecommerce_yt.mapper.review.ReviewMapper;
import com.paritoshpal.ecommerce_yt.model.Product;
import com.paritoshpal.ecommerce_yt.model.Size;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring" , uses = {ReviewMapper.class, RatingMapper.class})
public interface ProductMapper {

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "sizes", source = "sizes")
    Product toProduct(ProductRequestDTO productRequestDTO);

    @Mapping(target = "topLevelCategory", ignore = true)
    @Mapping(target = "secondLevelCategory", ignore = true)
    @Mapping(target = "thirdLevelCategory", ignore = true)
    ProductResponseDTO toProductResponseDTO(Product product);

}
