package com.paritoshpal.ecommerce_yt.mapper.category;

import com.paritoshpal.ecommerce_yt.dto.category.CategoryRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.category.CategoryResponseDTO;
import com.paritoshpal.ecommerce_yt.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T15:33:31+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toCategory(CategoryRequestDTO categoryRequestDTO) {
        if ( categoryRequestDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setParentCategory( categoryRequestDTOToCategory( categoryRequestDTO ) );
        category.setName( categoryRequestDTO.getName() );

        return category;
    }

    @Override
    public CategoryResponseDTO toCategoryResponseDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();

        categoryResponseDTO.setParentCategoryId( categoryParentCategoryId( category ) );
        categoryResponseDTO.setId( category.getId() );
        categoryResponseDTO.setName( category.getName() );

        return categoryResponseDTO;
    }

    protected Category categoryRequestDTOToCategory(CategoryRequestDTO categoryRequestDTO) {
        if ( categoryRequestDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryRequestDTO.getParentCategoryId() );

        return category;
    }

    private Long categoryParentCategoryId(Category category) {
        Category parentCategory = category.getParentCategory();
        if ( parentCategory == null ) {
            return null;
        }
        return parentCategory.getId();
    }
}
