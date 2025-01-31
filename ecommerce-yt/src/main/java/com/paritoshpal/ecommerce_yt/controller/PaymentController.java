package com.paritoshpal.ecommerce_yt.controller;

import com.paritoshpal.ecommerce_yt.response.ApiResponse;
import com.paritoshpal.ecommerce_yt.response.PaymentLinkResponse;
import com.paritoshpal.ecommerce_yt.service.payment.PaymentService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay/{orderId}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@PathVariable Long orderId) throws RazorpayException {
        PaymentLinkResponse response = paymentService.createPaymentLink(orderId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/redirect")
    public ResponseEntity<ApiResponse<String>> handlePaymentCallback(
            @RequestParam("payment_id") String paymentId,
            @RequestParam("order_id") Long orderId) throws RazorpayException {

        paymentService.handlePaymentCallback(paymentId, orderId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Payment successful");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
