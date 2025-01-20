package com.paritoshpal.ecommerce_yt.controller;

import com.paritoshpal.ecommerce_yt.dto.review.ReviewRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.review.ReviewResponseDTO;
import com.paritoshpal.ecommerce_yt.response.ApiResponse;
import com.paritoshpal.ecommerce_yt.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ReviewResponseDTO>> createReview(@RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewResponseDTO createdReview = reviewService.createReview(reviewRequestDTO);
        ApiResponse<ReviewResponseDTO> response = new ApiResponse<>(createdReview, "Review created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponseDTO>> getReview(@PathVariable Long id) {
        ReviewResponseDTO review = reviewService.getReview(id);
        ApiResponse<ReviewResponseDTO> response = new ApiResponse<>(review, "Review fetched successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<ReviewResponseDTO>>> getReviewsByUserId(@PathVariable Long userId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByUserId(userId);
        ApiResponse<List<ReviewResponseDTO>> response = new ApiResponse<>(reviews, "Reviews fetched successfully for user");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse<List<ReviewResponseDTO>>> getReviewsByProductId(@PathVariable Long productId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByProductId(productId);
        ApiResponse<List<ReviewResponseDTO>> response = new ApiResponse<>(reviews, "Reviews fetched successfully for product");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteReview(@PathVariable Long id) {
        String message = reviewService.deleteReview(id);
        ApiResponse<String> response = new ApiResponse<>(message, "Review deleted successfully");
        return ResponseEntity.ok(response);
    }

}
