package com.paritoshpal.ecommerce_yt.mapper.address;


import com.paritoshpal.ecommerce_yt.dto.address.AddressDTO;
import com.paritoshpal.ecommerce_yt.dto.address.AddressRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.address.AddressResponseDTO;
import com.paritoshpal.ecommerce_yt.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )

public interface AddressMapper {
    // Remember to get User and Set User In the Service Layer !
    Address toAddress(AddressRequestDTO addressRequestDTO);


    AddressDTO toAddressDTO(Address address);

    @Mapping(source = "user.id" ,target = "userId")
    AddressResponseDTO toAddressResponseDTO(Address address);

}
