package com.paritoshpal.ecommerce_yt.dto.product;

import com.paritoshpal.ecommerce_yt.model.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProductRequestDTO {

    @NotBlank
    @jakarta.validation.constraints.Size(max = 90)
    private String title;

    @NotBlank
    @jakarta.validation.constraints.Size(max = 230)
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @Min(value = 0)
    private BigDecimal discountedPrice;

    @Min(value = 0)
    private int discountPercentage;

    @NotNull
    @Positive
    private int quantity;

    @NotBlank
    @jakarta.validation.constraints.Size(max = 50)
    private String brand;

    @NotBlank
    @jakarta.validation.constraints.Size(max = 50)
    private String color;

    private Set<Size> sizes ;

    @NotBlank
    private String imageUrl;

    @NotNull
    private String topLevelCategory;

    private String secondLevelCategory;

    private String thirdLevelCategory;


}
