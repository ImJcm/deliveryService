package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name="order_menu")
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_menu_price")
    Integer orderMenuprice;
}
