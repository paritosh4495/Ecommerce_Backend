package com.paritoshpal.ecommerce_yt.mapper.cartItem;

import com.paritoshpal.ecommerce_yt.dto.cartItem.CartItemRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.cartItem.CartItemResponseDTO;
import com.paritoshpal.ecommerce_yt.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {



    CartItem toCartItem(CartItemRequestDTO cartItemRequestDTO);


    @Mapping(source = "product.id",target = "productId")
    @Mapping(source = "product.title",target = "title")
    @Mapping(source = "product.brand",target = "brand")
    @Mapping(source = "product.discountPercentage",target ="discountPercentage" )
    @Mapping(source = "product.imageUrl",target ="imageUrl" )
    @Mapping(source = "product.color",target ="color" )
    CartItemResponseDTO toCartItemResponseDTO(CartItem cartItem);
}
