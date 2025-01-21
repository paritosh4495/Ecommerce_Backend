package com.paritoshpal.ecommerce_yt.mapper.cart;


import com.paritoshpal.ecommerce_yt.dto.cart.CartResponseDTO;
import com.paritoshpal.ecommerce_yt.mapper.cartItem.CartItemMapper;
import com.paritoshpal.ecommerce_yt.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})

public interface CartMapper {

    @Mapping(source = "user.id",target = "userId")
    CartResponseDTO toCartResponseDTO(Cart cart);

}
