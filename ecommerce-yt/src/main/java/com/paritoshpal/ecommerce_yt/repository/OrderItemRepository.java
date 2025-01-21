package com.paritoshpal.ecommerce_yt.repository;

import com.paritoshpal.ecommerce_yt.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
