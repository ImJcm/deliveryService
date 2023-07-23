package com.sprta.deliveryproject.repository;

import com.sprta.deliveryproject.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByOrderId(Long orders_id);

    List<Cart> findAllByMemberIdAndShopIdAndOrderIdIsNull(Long member_id, Long shop_id);

    List<Cart> findAllByMemberIdAndOrderId(Long member_id, Long orders_id);

    List<Cart> findAllByMemberIdAndOrderIdIsNull(Long id);
}
