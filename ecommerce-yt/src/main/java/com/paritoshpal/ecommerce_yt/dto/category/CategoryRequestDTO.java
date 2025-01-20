package com.paritoshpal.ecommerce_yt.dto.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDTO {

    @NotNull
    @Size(max = 50)

    private String name;
    private Long parentCategoryId;
}
