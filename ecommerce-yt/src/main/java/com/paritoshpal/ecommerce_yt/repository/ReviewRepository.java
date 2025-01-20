package com.paritoshpal.ecommerce_yt.repository;

import com.paritoshpal.ecommerce_yt.model.Review;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByProductIdAndUserId(@NotNull Long productId, Long userId);

    List<Review> findByUserId(Long userId);

    List<Review> findByProductId(Long productId);
}
