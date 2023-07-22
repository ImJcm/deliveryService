package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cart")
public class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "shop_id",nullable = false)
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "menu_id",nullable = false)
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Cart(Integer amount, Member member, Shop shop, Menu menu, Order order){
        this.amount = amount;
        this.member = member;
        this.shop = shop;
        this.menu = menu;
        this.order = order;
    }
}
