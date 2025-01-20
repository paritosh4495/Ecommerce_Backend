package com.paritoshpal.ecommerce_yt.repository;

import com.paritoshpal.ecommerce_yt.model.Rating;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    boolean existsByProductIdAndUserId(@NotNull Long productId, Long userId);

    List<Rating> findByUserId(Long userId);

    List<Rating> findByProductId(Long productId);
}
