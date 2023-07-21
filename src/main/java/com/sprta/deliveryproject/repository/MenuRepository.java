package com.sprta.deliveryproject.repository;

import com.sprta.deliveryproject.entity.Menu;
import com.sprta.deliveryproject.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findAllByShop_IdOrderByMenunameDesc(Long id);

}
