package com.paritoshpal.ecommerce_yt.service.cart;

import com.paritoshpal.ecommerce_yt.dto.cart.CartRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.cart.CartResponseDTO;
import com.paritoshpal.ecommerce_yt.exception.*;
import com.paritoshpal.ecommerce_yt.mapper.cart.CartMapper;
import com.paritoshpal.ecommerce_yt.model.Cart;
import com.paritoshpal.ecommerce_yt.model.CartItem;
import com.paritoshpal.ecommerce_yt.model.Product;
import com.paritoshpal.ecommerce_yt.model.User;
import com.paritoshpal.ecommerce_yt.repository.CartRepository;
import com.paritoshpal.ecommerce_yt.repository.ProductRepository;
import com.paritoshpal.ecommerce_yt.repository.UserRepository;
import com.paritoshpal.ecommerce_yt.security.user.CustomUserDetails;
import com.paritoshpal.ecommerce_yt.service.cartItem.CartItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
@Transactional
public class CartServiceImpl implements CartService {


    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final UserRepository userRepository;
    private final CartItemService  cartItemService;
    private final ProductRepository productRepository;

    @Override
    public CartResponseDTO addCartItem(CartRequestDTO cartRequestDTO) {
        // Fetch the current User !
        Long userId = getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found"));


        // Fetch Cart for User If not found create one

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(()->{
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    cartRepository.save(newCart);
                    return newCart;
                });

        // Check Inventory! !
        Product product = productRepository.findById(cartRequestDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        if (product.getQuantity() < cartRequestDTO.getQuantity()) {
            throw new InsufficientInventoryException("Insufficient inventory for product: " + product.getTitle());
        }


        // Create and add Cart ITem
        CartItem cartItem = cartItemService.createCartItem(cartRequestDTO);
        cart.getCartItems().add(cartItem);
        updateCartTotals(cart);
        // SAVE AND RETURN CART
        cartRepository.save(cart);
        return cartMapper.toCartResponseDTO(cart);

    }

    @Override
    public CartResponseDTO findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if(cart == null) {
            throw new CartNotFoundException("Cart not found");
        }
        return cartMapper.toCartResponseDTO(cart);
    }

    @Override
    public CartResponseDTO getCurrentUserCart() {
        Long userId = getCurrentUserId();
        Cart cart = cartRepository.findByUserId(userId);
        if(cart == null) {
            throw new CartNotFoundException("Cart not found");
        }
        return cartMapper.toCartResponseDTO(cart);
    }

    @Override
    public String clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found");
        }
        cart.getCartItems().clear();
        cart.setTotalItems(0);
        cart.setTotalPrice(BigDecimal.ZERO);
        cart.setTotalDiscountedPrice(BigDecimal.ZERO);
        cart.setDiscount(0);
        cartRepository.save(cart);
        return "Cart Cleared Successfully";
    }

    @Override
    public CartResponseDTO removeCartItem(Long cartItemId) {
        Long userId = getCurrentUserId();
        Cart cart = cartRepository.findByUserId(userId);
        if(cart == null) {
            throw new CartNotFoundException("Cart not found");
        }
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new CartItemNotFoundException("CartItem not found"));
        cart.getCartItems().remove(cartItem);
        cartItemService.removeCartItem(cartItemId);
        updateCartTotals(cart);
        cartRepository.save(cart);
        return cartMapper.toCartResponseDTO(cart);
    }

    @Override
    public CartResponseDTO decreaseCartItemQuantity(Long cartItemId) {
        Long userId = getCurrentUserId();
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found");
        }
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new CartItemNotFoundException("CartItem not found"));

        int newQuantity = cartItem.getQuantity() - 1;
        if (newQuantity <= 0) {
            cart.getCartItems().remove(cartItem);
            cartItemService.removeCartItem(cartItemId);
        } else {
            cartItem.setQuantity(newQuantity);
            cartItem.setPrice(cartItem.getPrice().subtract(cartItem.getProduct().getPrice()));
            cartItem.setDiscountedPrice(cartItem.getDiscountedPrice().subtract(cartItem.getProduct().getDiscountedPrice()));
        }

        updateCartTotals(cart);
        cartRepository.save(cart);
        return cartMapper.toCartResponseDTO(cart);
    }

    @Override
    public void initializeEmptyCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setTotalItems(0);
        cart.setTotalPrice(BigDecimal.ZERO);
        cart.setTotalDiscountedPrice(BigDecimal.ZERO);
        cart.setDiscount(0);
        cartRepository.save(cart);
    }

    private void updateCartTotals(Cart cart) {
        int totalItems = cart.getCartItems().stream().mapToInt(CartItem::getQuantity).sum();
        BigDecimal totalPrice = cart.getCartItems().stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalDiscountedPrice = cart.getCartItems().stream()
                .map(CartItem::getDiscountedPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        int discount = totalPrice.subtract(totalDiscountedPrice).intValue();

        cart.setTotalItems(totalItems);
        cart.setTotalPrice(totalPrice);
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setDiscount(discount);
    }
    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}
