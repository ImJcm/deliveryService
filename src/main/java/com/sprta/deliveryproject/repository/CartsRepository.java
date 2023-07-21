package com.sprta.deliveryproject.repository;

import com.sprta.deliveryproject.entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartsRepository extends JpaRepository<Carts, Long> {

    List<Carts> findAllByMemberIdAndOrderIdIsNull(Long id);
}
