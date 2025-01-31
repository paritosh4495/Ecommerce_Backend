package com.paritoshpal.ecommerce_yt.service.payment;

import com.paritoshpal.ecommerce_yt.response.PaymentLinkResponse;
import com.razorpay.RazorpayException;

public interface PaymentService {

    PaymentLinkResponse createPaymentLink(Long orderId) throws RazorpayException;
    void handlePaymentCallback(String paymentId, Long orderId) throws RazorpayException;

}
