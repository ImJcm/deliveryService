package com.sprta.deliveryproject.repository;

import com.sprta.deliveryproject.entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CartsRepository extends JpaRepository<Carts, Long> {

    List<Carts> findAllByMemberIdAndOrderIdIsNull(Long id);

    Carts findByMenuId(Long menuId);

    Carts findTopByShopIdAndOrderIdIsNull(Long shopId);


    Optional<Object> findTopByMemberId(Long id);

    List<Carts> findAllByOrderId(Long id);
}
