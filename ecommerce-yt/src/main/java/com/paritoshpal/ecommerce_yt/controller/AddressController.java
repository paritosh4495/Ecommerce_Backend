package com.paritoshpal.ecommerce_yt.controller;


import com.paritoshpal.ecommerce_yt.dto.address.AddressResponseDTO;
import com.paritoshpal.ecommerce_yt.dto.product.ProductRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.product.ProductResponseDTO;
import com.paritoshpal.ecommerce_yt.response.ApiResponse;
import com.paritoshpal.ecommerce_yt.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<AddressResponseDTO>>> getAddresses(

    ) {
        List<AddressResponseDTO> addressList = addressService.getUserAddresses();
        ApiResponse<List<AddressResponseDTO>> response = new ApiResponse<>(addressList, "Address fetched successfully");
        return ResponseEntity.ok(response);
    }


}
