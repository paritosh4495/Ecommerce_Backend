package com.paritoshpal.ecommerce_yt.service.cartItem;

import com.paritoshpal.ecommerce_yt.dto.cart.CartRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.cartItem.CartItemRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.cartItem.CartItemResponseDTO;
import com.paritoshpal.ecommerce_yt.model.Cart;
import com.paritoshpal.ecommerce_yt.model.CartItem;

public interface CartItemService {

    CartItem createCartItem(CartRequestDTO cartRequestDTO);

    boolean doesCartItemExist(Long productId, String size);
    String removeCartItem(Long cartItemId);
    CartItemResponseDTO getCartItem(Long cartItemId);

}
