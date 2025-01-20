package com.paritoshpal.ecommerce_yt.service.user;

import com.paritoshpal.ecommerce_yt.dto.user.UserRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.user.UserResponseDTO;
import com.paritoshpal.ecommerce_yt.enums.Role;
import com.paritoshpal.ecommerce_yt.exception.EmailAlreadyExistsException;
import com.paritoshpal.ecommerce_yt.exception.UserNotFoundException;
import com.paritoshpal.ecommerce_yt.mapper.user.UserMapper;
import com.paritoshpal.ecommerce_yt.model.User;
import com.paritoshpal.ecommerce_yt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) {
            throw new IllegalArgumentException("User data cannot be null");
        }
        if(userRepository.existsByEmail(userRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        if (userRequestDTO.getPassword() == null || userRequestDTO.getPassword().isEmpty() || userRequestDTO.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
//        System.out.println("----------------------+++++________________________++++++++++++");
//        System.out.println(userRequestDTO.getPassword());


        User user = userMapper.toUser(userRequestDTO);

//        System.out.println("()))))))))))))))))))))))))))))()(");
//        System.out.println(user.getPassword()+" BASTARD PARI");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role defaultRole = Role.USER;
        user.setRole(defaultRole);
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("User with id " + id + " not found"));
        existingUser.setFirstName(userRequestDTO.getFirstName());
        existingUser.setLastName(userRequestDTO.getLastName());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toUserResponseDTO(updatedUser);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("User with id " + id + " not found"));
        return userMapper.toUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User with email " + email + " not found"));
        return userMapper.toUserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponseDTO).collect(Collectors.toList());
    }

    @Override
    public String deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User Not found with id: " + id));
        userRepository.deleteById(id);
        return "User deleted";
    }
}
