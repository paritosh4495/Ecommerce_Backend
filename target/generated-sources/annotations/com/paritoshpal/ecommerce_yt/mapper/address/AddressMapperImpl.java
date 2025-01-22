package com.paritoshpal.ecommerce_yt.mapper.address;

import com.paritoshpal.ecommerce_yt.dto.address.AddressDTO;
import com.paritoshpal.ecommerce_yt.dto.address.AddressRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.address.AddressResponseDTO;
import com.paritoshpal.ecommerce_yt.model.Address;
import com.paritoshpal.ecommerce_yt.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-22T13:32:17+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toAddress(AddressRequestDTO addressRequestDTO) {
        if ( addressRequestDTO == null ) {
            return null;
        }

        Address address = new Address();

        address.setFirstName( addressRequestDTO.getFirstName() );
        address.setLastName( addressRequestDTO.getLastName() );
        address.setStreetAddress( addressRequestDTO.getStreetAddress() );
        address.setCity( addressRequestDTO.getCity() );
        address.setState( addressRequestDTO.getState() );
        address.setZipCode( addressRequestDTO.getZipCode() );
        address.setMobile( addressRequestDTO.getMobile() );

        return address;
    }

    @Override
    public AddressDTO toAddressDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setStreetAddress( address.getStreetAddress() );
        addressDTO.setCity( address.getCity() );
        addressDTO.setState( address.getState() );
        addressDTO.setZipCode( address.getZipCode() );

        return addressDTO;
    }

    @Override
    public AddressResponseDTO toAddressResponseDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();

        addressResponseDTO.setUserId( addressUserId( address ) );
        addressResponseDTO.setId( address.getId() );
        addressResponseDTO.setFirstName( address.getFirstName() );
        addressResponseDTO.setLastName( address.getLastName() );
        addressResponseDTO.setStreetAddress( address.getStreetAddress() );
        addressResponseDTO.setCity( address.getCity() );
        addressResponseDTO.setState( address.getState() );
        addressResponseDTO.setZipCode( address.getZipCode() );
        addressResponseDTO.setMobile( address.getMobile() );

        return addressResponseDTO;
    }

    private Long addressUserId(Address address) {
        User user = address.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }
}
