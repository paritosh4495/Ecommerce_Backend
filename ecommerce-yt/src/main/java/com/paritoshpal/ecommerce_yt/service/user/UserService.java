package com.paritoshpal.ecommerce_yt.service.user;

import com.paritoshpal.ecommerce_yt.dto.user.UserRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.user.UserResponseDTO;


import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    UserResponseDTO getUserById(Long id);
    UserResponseDTO getUserByEmail(String email);

    List<UserResponseDTO> getAllUsers();
    String deleteUserById(Long id);



}
