package com.sprta.deliveryproject.repository;

import com.sprta.deliveryproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
