package com.sprta.deliveryproject.repository;

import com.sprta.deliveryproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {


     List<Order> findAllByMemberId(Long memberId);
}
