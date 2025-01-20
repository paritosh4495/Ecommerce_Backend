package com.paritoshpal.ecommerce_yt.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
