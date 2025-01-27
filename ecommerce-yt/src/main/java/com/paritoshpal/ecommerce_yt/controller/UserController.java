package com.paritoshpal.ecommerce_yt.controller;

import com.paritoshpal.ecommerce_yt.dto.user.UserRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.user.UserResponseDTO;
import com.paritoshpal.ecommerce_yt.response.ApiResponse;
import com.paritoshpal.ecommerce_yt.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(
            @Valid @RequestBody UserRequestDTO userRequestDTO
    ) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        ApiResponse<UserResponseDTO> response = new ApiResponse<>(createdUser, "User created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDTO userRequestDTO
    ) {
        UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
        ApiResponse<UserResponseDTO> response = new ApiResponse<>(updatedUser, "User updated successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserById(
            @PathVariable Long id
    ) {
        UserResponseDTO user = userService.getUserById(id);
        ApiResponse<UserResponseDTO> response = new ApiResponse<>(user, "User fetched successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN') or #email == principal.username")
    @GetMapping("/email")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserByEmail(
            @RequestParam String email
    ) {
        UserResponseDTO user = userService.getUserByEmail(email);
        ApiResponse<UserResponseDTO> response = new ApiResponse<>(user, "User fetched successfully by email");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAllUsers() {
        List<UserResponseDTO> allUsers = userService.getAllUsers();
        ApiResponse<List<UserResponseDTO>> response = new ApiResponse<>(allUsers, "All users fetched successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUserById(
            @PathVariable Long id
    ) {
        String message = userService.deleteUserById(id);
        ApiResponse<String> response = new ApiResponse<>(message, "User deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getCurrentUserProfile() {
        UserResponseDTO currentUser = userService.getCurrentUserProfile();
        ApiResponse<UserResponseDTO> response = new ApiResponse<>(currentUser, "User profile fetched successfully");
        return ResponseEntity.ok(response);
    }
}
