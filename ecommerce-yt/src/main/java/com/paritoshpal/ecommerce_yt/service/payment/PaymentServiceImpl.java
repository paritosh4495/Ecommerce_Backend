package com.paritoshpal.ecommerce_yt.service.payment;

import com.paritoshpal.ecommerce_yt.enums.OrderStatus;
import com.paritoshpal.ecommerce_yt.enums.PaymentStatus;
import com.paritoshpal.ecommerce_yt.exception.OrderNotFoundException;
import com.paritoshpal.ecommerce_yt.model.Order;
import com.paritoshpal.ecommerce_yt.repository.OrderRepository;
import com.paritoshpal.ecommerce_yt.response.PaymentLinkResponse;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor

public class PaymentServiceImpl implements PaymentService {

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    private final OrderRepository orderRepository;


    @Override
    public PaymentLinkResponse createPaymentLink(Long orderId) throws RazorpayException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", order.getTotalPrice().multiply(BigDecimal.valueOf(100)));
        paymentLinkRequest.put("currency", "INR");

        JSONObject customer = new JSONObject();
        customer.put("name", order.getUser().getFirstName() + " " + order.getUser().getLastName());
        customer.put("email", order.getUser().getEmail());
        paymentLinkRequest.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("sms", true);
        notify.put("email", true);
        paymentLinkRequest.put("notify", notify);

        paymentLinkRequest.put("callback_url", "https://dhruvzonecommerce.vercel.app/payment/" + orderId);
        paymentLinkRequest.put("callback_method", "get");

        PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);
        System.out.println(paymentLink);
        return new PaymentLinkResponse( paymentLink.get("short_url"),paymentLink.get("id"));
    }

    @Transactional
    @Override
    public void handlePaymentCallback(String paymentId, Long orderId) throws RazorpayException {
        RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        Payment payment = razorpayClient.payments.fetch(paymentId);
        System.out.println("PAYMENT :::::::::: " + payment);
        if (payment.get("status").equals("captured")) {
            order.getPaymentDetails().setPaymentId(paymentId);
            order.getPaymentDetails().setStatus(PaymentStatus.COMPLETED);
            order.setOrderStatus(OrderStatus.PLACED);
            orderRepository.save(order);
        }
    }
}
