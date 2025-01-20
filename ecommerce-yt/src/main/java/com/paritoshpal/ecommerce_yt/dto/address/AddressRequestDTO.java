package com.paritoshpal.ecommerce_yt.dto.address;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressRequestDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String streetAddress;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String zipCode;

    @NotNull
    private String mobile;

}
