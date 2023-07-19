package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="orders")
public class Order extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="payment_method")
    private String paymentmethod;   //결제 방법

    @Column(name="request")
    private String request; //요청사항

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "order")
    List<OrderMenu> orderMenuList = new ArrayList<>();
}
