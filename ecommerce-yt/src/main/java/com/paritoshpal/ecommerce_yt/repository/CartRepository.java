package com.paritoshpal.ecommerce_yt.repository;

import com.paritoshpal.ecommerce_yt.model.Cart;
import com.paritoshpal.ecommerce_yt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);

    Optional<Cart> findByUser(User user);
}
