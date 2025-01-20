package com.paritoshpal.ecommerce_yt.mapper.category;

import com.paritoshpal.ecommerce_yt.dto.category.CategoryRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.category.CategoryResponseDTO;
import com.paritoshpal.ecommerce_yt.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "parentCategory.id", source = "parentCategoryId")
    Category toCategory(CategoryRequestDTO categoryRequestDTO);

    @Mapping(target = "parentCategoryId", source = "parentCategory.id")
    CategoryResponseDTO toCategoryResponseDTO(Category category);
}
