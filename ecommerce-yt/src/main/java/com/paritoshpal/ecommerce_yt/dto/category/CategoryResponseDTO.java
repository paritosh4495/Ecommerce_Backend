package com.paritoshpal.ecommerce_yt.dto.category;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseDTO {

    private Long id;
    private String name;
    private Long parentCategoryId;
    private List<CategoryResponseDTO> childCategories;
}
