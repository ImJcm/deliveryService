package com.sprta.deliveryproject.repository;

import com.sprta.deliveryproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


     List<Order> findAllByMemberId(Long memberId);
}
