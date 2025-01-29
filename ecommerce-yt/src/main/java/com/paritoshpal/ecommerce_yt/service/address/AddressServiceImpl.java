package com.paritoshpal.ecommerce_yt.service.address;

import com.paritoshpal.ecommerce_yt.dto.address.AddressRequestDTO;
import com.paritoshpal.ecommerce_yt.model.Address;
import com.paritoshpal.ecommerce_yt.model.User;
import com.paritoshpal.ecommerce_yt.repository.AddressRepository;
import com.paritoshpal.ecommerce_yt.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {



    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

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

    private boolean equalsIgnoreCaseAndTrim(String str1, String str2) {
        if (str1 == null || str2 == null) return false;
        return str1.trim().equalsIgnoreCase(str2.trim());
    }

}
