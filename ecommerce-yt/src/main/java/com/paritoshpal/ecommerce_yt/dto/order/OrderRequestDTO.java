package com.paritoshpal.ecommerce_yt.dto.order;

import com.paritoshpal.ecommerce_yt.model.Address;
import lombok.Data;

@Data
public class OrderRequestDTO {

    private Long userId;
    private Address address;


}
