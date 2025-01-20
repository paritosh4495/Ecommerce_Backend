package com.paritoshpal.ecommerce_yt.exception;

import java.io.Serializable;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
