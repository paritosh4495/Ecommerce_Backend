package com.paritoshpal.ecommerce_yt.controller;

import com.paritoshpal.ecommerce_yt.dto.cartItem.CartItemResponseDTO;
import com.paritoshpal.ecommerce_yt.response.ApiResponse;
import com.paritoshpal.ecommerce_yt.service.cartItem.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cartItem")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse<CartItemResponseDTO>> getCartItem(
            @PathVariable Long cartItemId
    ) {
        CartItemResponseDTO cartItemResponseDTO = cartItemService.getCartItem(cartItemId);
        ApiResponse<CartItemResponseDTO> response = new ApiResponse<>(cartItemResponseDTO, "Cart item retrieved successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
