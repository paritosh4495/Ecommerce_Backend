package com.paritoshpal.ecommerce_yt.service.rating;

import com.paritoshpal.ecommerce_yt.dto.rating.RatingRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.rating.RatingResponseDTO;
import com.paritoshpal.ecommerce_yt.exception.ProductNotFoundException;
import com.paritoshpal.ecommerce_yt.exception.RatingNotFoundException;
import com.paritoshpal.ecommerce_yt.exception.UnauthorizedException;
import com.paritoshpal.ecommerce_yt.exception.UserNotFoundException;
import com.paritoshpal.ecommerce_yt.mapper.rating.RatingMapper;
import com.paritoshpal.ecommerce_yt.model.Product;
import com.paritoshpal.ecommerce_yt.model.Rating;
import com.paritoshpal.ecommerce_yt.model.User;
import com.paritoshpal.ecommerce_yt.repository.ProductRepository;
import com.paritoshpal.ecommerce_yt.repository.RatingRepository;
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
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final RatingMapper ratingMapper;

    @Override
    public RatingResponseDTO createRating(RatingRequestDTO ratingRequestDTO) {
        if(ratingRequestDTO == null){
            throw new IllegalArgumentException("RatingRequestDTO is null");
        }

        // Get the product for which rating is being created !
        Product product = productRepository.findById(ratingRequestDTO.getProductId()).orElseThrow(()-> new ProductNotFoundException("Product not found"));

        // Get User Who is adding this rating !

        Long userId = getCurrentUserId();

        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));

        boolean ratingExists = ratingRepository.existsByProductIdAndUserId(ratingRequestDTO.getProductId(),userId);
        if(ratingExists){
            throw new IllegalArgumentException("You have already added a rating to this product");
        }
        Rating rating = ratingMapper.toRating(ratingRequestDTO);
        rating.setProduct(product);
        rating.setUser(user);
        ratingRepository.save(rating);
        return ratingMapper.toRatingResponseDTO(rating);

    }

    @Override
    public RatingResponseDTO updateRating(Long ratingId, RatingRequestDTO ratingRequestDTO) {
        if(ratingRequestDTO == null){
            throw new IllegalArgumentException("RatingRequestDTO is null");
        }

        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(()-> new RatingNotFoundException("Rating not found"));

        Long userId = getCurrentUserId();

        if(!rating.getUser().getId().equals(userId)){
            throw new UnauthorizedException("You are not authorized to update this rating");
        }

        rating.setRating(ratingRequestDTO.getRating());
        ratingRepository.save(rating);
        return ratingMapper.toRatingResponseDTO(rating);


    }

    @Override
    public RatingResponseDTO getRating(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(()-> new RatingNotFoundException("Rating not found"));
        return ratingMapper.toRatingResponseDTO(rating);
    }

    @Override
    public List<RatingResponseDTO> getRatingsByUserId(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));

        List<Rating> ratings = ratingRepository.findByUserId(userId);
        return ratings.stream().map(ratingMapper::toRatingResponseDTO).collect(Collectors.toList());

    }

    @Override
    public List<RatingResponseDTO> getRatingsByProductId(Long productId) {

        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product not found"));
        List<Rating> ratings = ratingRepository.findByProductId(productId);
        return ratings.stream().map(ratingMapper::toRatingResponseDTO).collect(Collectors.toList());

    }

    @Override
    public String deleteRating(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(()-> new RatingNotFoundException("Rating not found"));
        Long userId = getCurrentUserId();
        if(!rating.getUser().getId().equals(userId)){
            throw new UnauthorizedException("You are not authorized to update this rating");
        }
        ratingRepository.delete(rating);
        return "Rating deleted";

    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}
