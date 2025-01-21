package com.paritoshpal.ecommerce_yt.service.cart;

import com.paritoshpal.ecommerce_yt.dto.cart.CartRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.cart.CartResponseDTO;
import com.paritoshpal.ecommerce_yt.model.Cart;
import com.paritoshpal.ecommerce_yt.model.User;

public interface CartService {

    CartResponseDTO addCartItem(CartRequestDTO cartRequestDTO);

    CartResponseDTO findUserCart(Long userId);

    CartResponseDTO removeCartItem(Long cartItemId);

    CartResponseDTO decreaseCartItemQuantity(Long cartItemId);

    void initializeEmptyCart(User user);


}
