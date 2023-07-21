package com.sprta.deliveryproject.repository;

import com.sprta.deliveryproject.entity.CartList;
import com.sprta.deliveryproject.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartListRepository extends JpaRepository<CartList, Long> {
    Optional<CartList> findTopByCartId(Long cartId);
    Long findByShopId(Long shopId);
    List<CartList> findByCartId(Long id);


    Optional<CartList> findByMenuId(Long menuId);

    CartList findByMenuName(String menuname);
}
