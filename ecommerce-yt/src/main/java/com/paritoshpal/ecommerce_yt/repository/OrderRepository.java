package com.paritoshpal.ecommerce_yt.repository;

import com.paritoshpal.ecommerce_yt.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
