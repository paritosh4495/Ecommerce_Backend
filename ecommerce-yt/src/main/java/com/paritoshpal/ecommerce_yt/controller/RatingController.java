package com.paritoshpal.ecommerce_yt.controller;

import com.paritoshpal.ecommerce_yt.dto.rating.RatingRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.rating.RatingResponseDTO;
import com.paritoshpal.ecommerce_yt.response.ApiResponse;
import com.paritoshpal.ecommerce_yt.service.rating.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<RatingResponseDTO>> createRating(@RequestBody RatingRequestDTO ratingRequestDTO) {
        RatingResponseDTO createdRating = ratingService.createRating(ratingRequestDTO);
        ApiResponse<RatingResponseDTO> response = new ApiResponse<>(createdRating, "Rating created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<RatingResponseDTO>> updateRating(@PathVariable Long id, @RequestBody RatingRequestDTO ratingRequestDTO) {
        RatingResponseDTO updatedRating = ratingService.updateRating(id, ratingRequestDTO);
        ApiResponse<RatingResponseDTO> response = new ApiResponse<>(updatedRating, "Rating updated successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RatingResponseDTO>> getRating(@PathVariable Long id) {
        RatingResponseDTO rating = ratingService.getRating(id);
        ApiResponse<RatingResponseDTO> response = new ApiResponse<>(rating, "Rating fetched successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<RatingResponseDTO>>> getRatingsByUserId(@PathVariable Long userId) {
        List<RatingResponseDTO> ratings = ratingService.getRatingsByUserId(userId);
        ApiResponse<List<RatingResponseDTO>> response = new ApiResponse<>(ratings, "Ratings fetched successfully for user");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse<List<RatingResponseDTO>>> getRatingsByProductId(@PathVariable Long productId) {
        List<RatingResponseDTO> ratings = ratingService.getRatingsByProductId(productId);
        ApiResponse<List<RatingResponseDTO>> response = new ApiResponse<>(ratings, "Ratings fetched successfully for product");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteRating(@PathVariable Long id) {
        String message = ratingService.deleteRating(id);
        ApiResponse<String> response = new ApiResponse<>(message, "Rating deleted successfully");
        return ResponseEntity.ok(response);
    }
}
