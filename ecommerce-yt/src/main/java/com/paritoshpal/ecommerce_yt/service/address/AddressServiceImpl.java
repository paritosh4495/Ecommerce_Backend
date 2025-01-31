package com.paritoshpal.ecommerce_yt.service.address;

import com.paritoshpal.ecommerce_yt.dto.address.AddressRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.address.AddressResponseDTO;
import com.paritoshpal.ecommerce_yt.exception.UserNotFoundException;
import com.paritoshpal.ecommerce_yt.mapper.address.AddressMapper;
import com.paritoshpal.ecommerce_yt.model.Address;
import com.paritoshpal.ecommerce_yt.model.User;
import com.paritoshpal.ecommerce_yt.repository.AddressRepository;
import com.paritoshpal.ecommerce_yt.repository.UserRepository;
import com.paritoshpal.ecommerce_yt.security.user.CustomUserDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {



    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;

    @Override
    public Address findOrCreateAddress(User user, Address address) {
        return user.getAddresses().stream()
                .filter(addr -> equalsIgnoreCaseAndTrim(addr.getFirstName(), address.getFirstName()) &&
                        equalsIgnoreCaseAndTrim(addr.getLastName(), address.getLastName()) &&
                        equalsIgnoreCaseAndTrim(addr.getStreetAddress(), address.getStreetAddress()) &&
                        equalsIgnoreCaseAndTrim(addr.getCity(), address.getCity()) &&
                        equalsIgnoreCaseAndTrim(addr.getState(), address.getState()) &&
                        equalsIgnoreCaseAndTrim(addr.getZipCode(), address.getZipCode()) &&
                        equalsIgnoreCaseAndTrim(addr.getMobile(), address.getMobile()))
                .findFirst()
                .orElseGet(() -> createAndSaveAddress(user, address));
    }

    @Override
    public Address createAndSaveAddress(User user, Address address) {
        address.setUser(user);  // Ensure user linkage
        Address savedAddress = addressRepository.save(address);  // Save only once
        user.getAddresses().add(savedAddress);
        userRepository.save(user);  // Update user with the new address
        return savedAddress;
    }

    @Override
    public List<AddressResponseDTO> getUserAddresses() {
        Long userId = getCurrentUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        List<Address> addresses = user.getAddresses();

        // Correctly map addresses to AddressResponseDTO
        List<AddressResponseDTO> addressResponseDTOS = addresses.stream()
                .map(addressMapper::toAddressResponseDTO)
                .collect(Collectors.toList());

        return addressResponseDTOS;
    }


    private boolean equalsIgnoreCaseAndTrim(String str1, String str2) {
        if (str1 == null || str2 == null) return false;
        return str1.trim().equalsIgnoreCase(str2.trim());
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }

}
