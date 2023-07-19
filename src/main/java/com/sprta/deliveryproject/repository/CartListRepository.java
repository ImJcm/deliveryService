package com.sprta.deliveryproject.repository;

import com.sprta.deliveryproject.entity.CartList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartListRepository extends JpaRepository<CartList, Long> {
    CartList findByCartId(Long cartId);
}
