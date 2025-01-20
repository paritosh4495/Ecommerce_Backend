package com.paritoshpal.ecommerce_yt.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ApiResponse <T>{
    private T data;
    private String message;

    public ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }
}
