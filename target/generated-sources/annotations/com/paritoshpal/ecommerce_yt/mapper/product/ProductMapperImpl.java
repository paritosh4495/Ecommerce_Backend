package com.paritoshpal.ecommerce_yt.mapper.product;

import com.paritoshpal.ecommerce_yt.dto.product.ProductRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.product.ProductResponseDTO;
import com.paritoshpal.ecommerce_yt.dto.rating.RatingDTO;
import com.paritoshpal.ecommerce_yt.dto.review.ReviewDTO;
import com.paritoshpal.ecommerce_yt.mapper.rating.RatingMapper;
import com.paritoshpal.ecommerce_yt.mapper.review.ReviewMapper;
import com.paritoshpal.ecommerce_yt.model.Product;
import com.paritoshpal.ecommerce_yt.model.Rating;
import com.paritoshpal.ecommerce_yt.model.Review;
import com.paritoshpal.ecommerce_yt.model.Size;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-19T16:09:14+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private RatingMapper ratingMapper;

    @Override
    public Product toProduct(ProductRequestDTO productRequestDTO) {
        if ( productRequestDTO == null ) {
            return null;
        }

        Product product = new Product();

        Set<Size> set = productRequestDTO.getSizes();
        if ( set != null ) {
            product.setSizes( new LinkedHashSet<Size>( set ) );
        }
        product.setTitle( productRequestDTO.getTitle() );
        product.setDescription( productRequestDTO.getDescription() );
        product.setPrice( productRequestDTO.getPrice() );
        product.setDiscountedPrice( productRequestDTO.getDiscountedPrice() );
        product.setDiscountPercentage( productRequestDTO.getDiscountPercentage() );
        product.setQuantity( productRequestDTO.getQuantity() );
        product.setBrand( productRequestDTO.getBrand() );
        product.setColor( productRequestDTO.getColor() );
        product.setImageUrl( productRequestDTO.getImageUrl() );

        return product;
    }

    @Override
    public ProductResponseDTO toProductResponseDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId( product.getId() );
        productResponseDTO.setTitle( product.getTitle() );
        productResponseDTO.setDescription( product.getDescription() );
        productResponseDTO.setPrice( product.getPrice() );
        productResponseDTO.setDiscountedPrice( product.getDiscountedPrice() );
        productResponseDTO.setDiscountPercentage( product.getDiscountPercentage() );
        productResponseDTO.setQuantity( product.getQuantity() );
        productResponseDTO.setBrand( product.getBrand() );
        productResponseDTO.setColor( product.getColor() );
        productResponseDTO.setImageUrl( product.getImageUrl() );
        Set<Size> set = product.getSizes();
        if ( set != null ) {
            productResponseDTO.setSizes( new LinkedHashSet<Size>( set ) );
        }
        productResponseDTO.setRatings( ratingListToRatingDTOList( product.getRatings() ) );
        productResponseDTO.setReviews( reviewListToReviewDTOList( product.getReviews() ) );
        productResponseDTO.setNumRatings( product.getNumRatings() );
        productResponseDTO.setCreatedAt( product.getCreatedAt() );
        productResponseDTO.setUpdatedAt( product.getUpdatedAt() );

        return productResponseDTO;
    }

    protected List<RatingDTO> ratingListToRatingDTOList(List<Rating> list) {
        if ( list == null ) {
            return null;
        }

        List<RatingDTO> list1 = new ArrayList<RatingDTO>( list.size() );
        for ( Rating rating : list ) {
            list1.add( ratingMapper.toRatingDTO( rating ) );
        }

        return list1;
    }

    protected List<ReviewDTO> reviewListToReviewDTOList(List<Review> list) {
        if ( list == null ) {
            return null;
        }

        List<ReviewDTO> list1 = new ArrayList<ReviewDTO>( list.size() );
        for ( Review review : list ) {
            list1.add( reviewMapper.toReviewDTO( review ) );
        }

        return list1;
    }
}
