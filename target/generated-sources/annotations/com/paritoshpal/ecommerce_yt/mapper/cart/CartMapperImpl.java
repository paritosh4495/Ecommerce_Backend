package com.paritoshpal.ecommerce_yt.mapper.cart;

import com.paritoshpal.ecommerce_yt.dto.cart.CartResponseDTO;
import com.paritoshpal.ecommerce_yt.dto.cartItem.CartItemResponseDTO;
import com.paritoshpal.ecommerce_yt.mapper.cartItem.CartItemMapper;
import com.paritoshpal.ecommerce_yt.model.Cart;
import com.paritoshpal.ecommerce_yt.model.CartItem;
import com.paritoshpal.ecommerce_yt.model.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-30T22:36:37+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public CartResponseDTO toCartResponseDTO(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartResponseDTO cartResponseDTO = new CartResponseDTO();

        cartResponseDTO.setUserId( cartUserId( cart ) );
        cartResponseDTO.setId( cart.getId() );
        cartResponseDTO.setCartItems( cartItemSetToCartItemResponseDTOSet( cart.getCartItems() ) );
        cartResponseDTO.setTotalPrice( cart.getTotalPrice() );
        cartResponseDTO.setTotalItems( cart.getTotalItems() );
        cartResponseDTO.setTotalDiscountedPrice( cart.getTotalDiscountedPrice() );
        cartResponseDTO.setDiscount( cart.getDiscount() );

        return cartResponseDTO;
    }

    private Long cartUserId(Cart cart) {
        User user = cart.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    protected Set<CartItemResponseDTO> cartItemSetToCartItemResponseDTOSet(Set<CartItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<CartItemResponseDTO> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( CartItem cartItem : set ) {
            set1.add( cartItemMapper.toCartItemResponseDTO( cartItem ) );
        }

        return set1;
    }
}
