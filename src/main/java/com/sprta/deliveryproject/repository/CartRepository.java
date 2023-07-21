package com.sprta.deliveryproject.repository;

import com.sprta.deliveryproject.entity.Cart;
import com.sprta.deliveryproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberId(Long memberId);
}
