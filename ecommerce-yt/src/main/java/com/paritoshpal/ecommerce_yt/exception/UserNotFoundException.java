package com.paritoshpal.ecommerce_yt.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {

        super(message);
    }
}
