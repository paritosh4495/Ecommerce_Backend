package com.paritoshpal.ecommerce_yt.exception;

import com.paritoshpal.ecommerce_yt.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleEmailNotFoundException(EmailNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleCategoryNotFoundException(CategoryNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleProductNotFoundException(ProductNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleProductAlreadyExistsException(ProductAlreadyExistsException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleRatingNotFoundException(RatingNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleReviewNotFoundException(ReviewNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<String>> handleUnauthorizedException(UnauthorizedException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }


    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleOrderNotFoundException(OrderNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }



    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleCartItemNotFoundException(CartItemNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleCartNotFoundException(CartNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(OrderItemNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleOrderItemNotFoundException(OrderItemNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }


    @ExceptionHandler(InsufficientInventoryException.class)
    public ResponseEntity<ApiResponse<String>> handleInsufficientInventoryException(InsufficientInventoryException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalStateException( IllegalStateException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }







}
