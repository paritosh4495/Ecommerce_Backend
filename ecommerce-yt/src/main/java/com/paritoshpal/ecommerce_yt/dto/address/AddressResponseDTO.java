package com.paritoshpal.ecommerce_yt.dto.address;

import lombok.Data;

@Data
public class AddressResponseDTO {

    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String mobile;

}
