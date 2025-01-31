package com.paritoshpal.ecommerce_yt.repository;

import com.paritoshpal.ecommerce_yt.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    Optional<Order> findByPaymentDetails_PaymentId(String razorpayOrderId);
}
