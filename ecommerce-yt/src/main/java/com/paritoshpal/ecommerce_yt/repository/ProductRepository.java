package com.paritoshpal.ecommerce_yt.repository;

import com.paritoshpal.ecommerce_yt.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitle(String name);

    List<Product> findByCategoryName(String category);

    boolean existsByTitle(@NotBlank @Size(max = 90) String title);

    @Query(
            "SELECT p FROM Product p "+
                    "WHERE (p.category.name = :category OR :category='')"+
                    "AND ((:minPrice IS NULL AND :maxPrice IS NULL)OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice))"+
                    "AND (:minDiscount IS NULL OR p.discountPercentage>= :minDiscount)"+
                    "ORDER BY "+
                    "CASE WHEN :sort = 'price_low' THEN p.discountedPrice END ASC,"+
                    "CASE WHEN :sort = 'price_high' THEN p.discountedPrice END DESC"
    )
    List<Product> getFilteredProducts(
            @Param("category") String category,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            @Param("minDiscount") Integer minDiscount,
            @Param("sort") String sort);

}
