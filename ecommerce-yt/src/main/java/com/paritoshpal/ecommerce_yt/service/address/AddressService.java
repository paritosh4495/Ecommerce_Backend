package com.paritoshpal.ecommerce_yt.service.address;

import com.paritoshpal.ecommerce_yt.dto.address.AddressRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.address.AddressResponseDTO;
import com.paritoshpal.ecommerce_yt.model.Address;
import com.paritoshpal.ecommerce_yt.model.User;

import java.util.List;

public interface AddressService {

    Address findOrCreateAddress(User user, Address address);
    Address createAndSaveAddress(User user, Address address);

    List<AddressResponseDTO> getUserAddresses();



}
