package com.paritoshpal.ecommerce_yt.service.cartItem;

import com.paritoshpal.ecommerce_yt.dto.cart.CartRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.cartItem.CartItemRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.cartItem.CartItemResponseDTO;
import com.paritoshpal.ecommerce_yt.exception.CartItemNotFoundException;
import com.paritoshpal.ecommerce_yt.exception.CartNotFoundException;
import com.paritoshpal.ecommerce_yt.exception.ProductNotFoundException;
import com.paritoshpal.ecommerce_yt.exception.UserNotFoundException;
import com.paritoshpal.ecommerce_yt.mapper.cartItem.CartItemMapper;
import com.paritoshpal.ecommerce_yt.model.Cart;
import com.paritoshpal.ecommerce_yt.model.CartItem;
import com.paritoshpal.ecommerce_yt.model.Product;
import com.paritoshpal.ecommerce_yt.model.User;
import com.paritoshpal.ecommerce_yt.repository.CartItemRepository;
import com.paritoshpal.ecommerce_yt.repository.CartRepository;
import com.paritoshpal.ecommerce_yt.repository.ProductRepository;
import com.paritoshpal.ecommerce_yt.repository.UserRepository;
import com.paritoshpal.ecommerce_yt.security.user.CustomUserDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Override
    public CartItem createCartItem(CartRequestDTO cartRequestDTO) {
        // Fetch Product
        Product product = productRepository.findById(cartRequestDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        // Get Current User
        Long userId = getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User Not Found"));

        // Get Cart By UserID
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(()->new CartNotFoundException("Cart NOT FOUND"));

        // Check if cartItem Already Exists
        // IF yes -> Increase the quantity !

        if(doesCartItemExist(cartRequestDTO.getProductId(),cartRequestDTO.getSize())){

            CartItem cartItem = cartItemRepository.getCartItemByProductAndSize(product,cartRequestDTO.getSize());
            cartItem.setQuantity(cartItem.getQuantity()+1);

            cartItem.setPrice(cartItem.getPrice().add(cartRequestDTO.getPrice().multiply(BigDecimal.valueOf(cartRequestDTO.getQuantity()))));
            cartItem.setDiscountedPrice(cartItem.getDiscountedPrice().add(product.getDiscountedPrice().multiply(BigDecimal.valueOf(cartRequestDTO.getQuantity()))));
            cartItemRepository.save(cartItem);
            return cartItem;
        }

        ///  IF nO -> Create NEW
        // Create CartItem
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setUserId(userId);
        cartItem.setProduct(product);
        cartItem.setSize(cartRequestDTO.getSize());
        cartItem.setQuantity(cartRequestDTO.getQuantity());
        cartItem.setPrice(cartRequestDTO.getPrice().multiply(BigDecimal.valueOf(cartRequestDTO.getQuantity())));
        cartItem.setDiscountedPrice(product.getDiscountedPrice().multiply(BigDecimal.valueOf(cartRequestDTO.getQuantity())));

        return cartItemRepository.save(cartItem);
    }

    @Override
    public boolean doesCartItemExist(Long productId, String size) {
        return cartItemRepository.existsByProductIdAndSize(productId,size);
    }

    @Override
    public String removeCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart Item Not Found"));

        cartItemRepository.delete(cartItem);
        return "Cart Item Removed Successfully";
    }

    @Override
    public CartItemResponseDTO getCartItem(Long cartItemId) {
        // Fetch cart item
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("CartItem not found"));

        // Return cart item
        return cartItemMapper.toCartItemResponseDTO(cartItem);
    }


    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}
