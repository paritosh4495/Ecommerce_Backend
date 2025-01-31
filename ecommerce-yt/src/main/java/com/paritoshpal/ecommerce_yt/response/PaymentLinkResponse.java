package com.paritoshpal.ecommerce_yt.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentLinkResponse {

    private String payment_link_url;
    private String payment_link_id;


}
