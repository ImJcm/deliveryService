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
    private Integer orderMenuprice;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    Menu menu;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

}
