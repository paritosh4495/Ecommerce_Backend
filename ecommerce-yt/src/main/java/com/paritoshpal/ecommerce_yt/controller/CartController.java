package com.paritoshpal.ecommerce_yt.controller;

import com.paritoshpal.ecommerce_yt.dto.cart.CartRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.cart.CartResponseDTO;
import com.paritoshpal.ecommerce_yt.response.ApiResponse;
import com.paritoshpal.ecommerce_yt.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CartResponseDTO>> addCartItem(
            @RequestBody CartRequestDTO cartRequestDTO
    ) {
        CartResponseDTO cartResponseDTO = cartService.addCartItem(cartRequestDTO);
        ApiResponse<CartResponseDTO> response = new ApiResponse<>(cartResponseDTO, "Item added to cart successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<CartResponseDTO>> getUserCart(
            @PathVariable Long userId
    ) {
        CartResponseDTO cartResponseDTO = cartService.findUserCart(userId);
        ApiResponse<CartResponseDTO> response = new ApiResponse<>(cartResponseDTO, "User cart retrieved successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<ApiResponse<CartResponseDTO>> removeCartItem(
            @PathVariable Long cartItemId
    ) {
        CartResponseDTO cartResponseDTO = cartService.removeCartItem(cartItemId);
        ApiResponse<CartResponseDTO> response = new ApiResponse<>(cartResponseDTO, "Item removed from cart successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/decrease/{cartItemId}")
    public ResponseEntity<ApiResponse<CartResponseDTO>> decreaseCartItemQuantity(@PathVariable Long cartItemId) {
        CartResponseDTO cartResponseDTO = cartService.decreaseCartItemQuantity(cartItemId);
        ApiResponse<CartResponseDTO> response = new ApiResponse<>(cartResponseDTO, "Item quantity decreased successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
