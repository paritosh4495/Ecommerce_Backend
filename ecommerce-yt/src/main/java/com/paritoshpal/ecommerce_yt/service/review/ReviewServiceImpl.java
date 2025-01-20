package com.paritoshpal.ecommerce_yt.service.review;

import com.paritoshpal.ecommerce_yt.dto.review.ReviewRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.review.ReviewResponseDTO;
import com.paritoshpal.ecommerce_yt.exception.*;
import com.paritoshpal.ecommerce_yt.mapper.review.ReviewMapper;
import com.paritoshpal.ecommerce_yt.model.Product;
import com.paritoshpal.ecommerce_yt.model.Rating;
import com.paritoshpal.ecommerce_yt.model.Review;
import com.paritoshpal.ecommerce_yt.model.User;
import com.paritoshpal.ecommerce_yt.repository.ProductRepository;
import com.paritoshpal.ecommerce_yt.repository.ReviewRepository;
import com.paritoshpal.ecommerce_yt.repository.UserRepository;
import com.paritoshpal.ecommerce_yt.security.user.CustomUserDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO) {
        if (reviewRequestDTO == null) {
            throw new IllegalArgumentException("ReviewRequestDTO is null");
        }

        Product product = productRepository.findById(reviewRequestDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Long userId = getCurrentUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        boolean reviewExists = reviewRepository.existsByProductIdAndUserId(reviewRequestDTO.getProductId(), userId);
        if (reviewExists) {
            throw new IllegalArgumentException("You have already added a review to this product");
        }

        Review review = reviewMapper.toReview(reviewRequestDTO);
        review.setProduct(product);
        review.setUser(user);

        reviewRepository.save(review);
        return reviewMapper.toReviewResponseDTO(review);
    }

    @Override
    public ReviewResponseDTO updateReview(Long reviewId, ReviewRequestDTO reviewRequestDTO) {
        if(reviewRequestDTO == null){
            throw new IllegalArgumentException("reviewRequestDTO is null");
        }

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new RatingNotFoundException("Rating not found"));

        Long userId = getCurrentUserId();

        if(!review.getUser().getId().equals(userId)){
            throw new UnauthorizedException("You are not authorized to update this Review");
        }

        review.setReview(reviewRequestDTO.getReview());
        reviewRepository.save(review);
        return reviewMapper.toReviewResponseDTO(review);

    }

    @Override
    public ReviewResponseDTO getReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found"));

        return reviewMapper.toReviewResponseDTO(review);
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        List<Review> reviews = reviewRepository.findByUserId(userId);
        return reviews.stream().map(reviewMapper::toReviewResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByProductId(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviews.stream().map(reviewMapper::toReviewResponseDTO).collect(Collectors.toList());
    }

    @Override
    public String deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found"));

        Long userId = getCurrentUserId();
        if (!review.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You are not authorized to delete this review");
        }

        reviewRepository.delete(review);
        return "Review deleted";
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}
