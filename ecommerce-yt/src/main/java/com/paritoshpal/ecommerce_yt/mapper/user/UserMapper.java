package com.paritoshpal.ecommerce_yt.mapper.user;

import com.paritoshpal.ecommerce_yt.dto.user.UserRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.user.UserResponseDTO;
import com.paritoshpal.ecommerce_yt.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toUser(UserRequestDTO userRequestDTO){
        if(userRequestDTO == null) return null;

        User user = new User();
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());

        return user;
    }

    public UserResponseDTO toUserResponseDTO(User user){
        if(user == null) return null;

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setMobile(user.getMobile());
        userResponseDTO.setCreatedAt(user.getCreatedAt());
        userResponseDTO.setUpdatedAt(user.getUpdatedAt());

        return userResponseDTO;
    }


}
