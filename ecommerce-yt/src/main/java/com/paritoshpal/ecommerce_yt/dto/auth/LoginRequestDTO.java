package com.paritoshpal.ecommerce_yt.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotNull
    private String email;

    @NotNull
    private String password;
}
