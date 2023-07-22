package com.sprta.deliveryproject.repository;

import com.sprta.deliveryproject.dto.CartResponseDto;
import com.sprta.deliveryproject.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByOrderId(Long id);

    List<Cart> findAllByMemberIdAndShopIdAndOrderIdIsNull(Long member_id, Long shop_id);

    List<Cart> findAllByMemberIdAndOrderId(Long id, Long order_id);
}
