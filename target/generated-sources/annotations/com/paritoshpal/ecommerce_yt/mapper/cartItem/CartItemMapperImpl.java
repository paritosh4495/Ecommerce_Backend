package com.paritoshpal.ecommerce_yt.mapper.cartItem;

import com.paritoshpal.ecommerce_yt.dto.cartItem.CartItemRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.cartItem.CartItemResponseDTO;
import com.paritoshpal.ecommerce_yt.model.CartItem;
import com.paritoshpal.ecommerce_yt.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T15:33:31+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class CartItemMapperImpl implements CartItemMapper {

    @Override
    public CartItem toCartItem(CartItemRequestDTO cartItemRequestDTO) {
        if ( cartItemRequestDTO == null ) {
            return null;
        }

        CartItem cartItem = new CartItem();

        cartItem.setQuantity( cartItemRequestDTO.getQuantity() );
        cartItem.setPrice( cartItemRequestDTO.getPrice() );
        cartItem.setDiscountedPrice( cartItemRequestDTO.getDiscountedPrice() );

        return cartItem;
    }

    @Override
    public CartItemResponseDTO toCartItemResponseDTO(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();

        cartItemResponseDTO.setProductId( cartItemProductId( cartItem ) );
        cartItemResponseDTO.setId( cartItem.getId() );
        cartItemResponseDTO.setSize( cartItem.getSize() );
        cartItemResponseDTO.setQuantity( cartItem.getQuantity() );
        cartItemResponseDTO.setPrice( cartItem.getPrice() );
        cartItemResponseDTO.setDiscountedPrice( cartItem.getDiscountedPrice() );

        return cartItemResponseDTO;
    }

    private Long cartItemProductId(CartItem cartItem) {
        Product product = cartItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }
}
