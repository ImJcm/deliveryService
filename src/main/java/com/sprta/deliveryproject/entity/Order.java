package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="orders")
public class Order extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String menuName;

    @Column
    private Integer amount;

    @Column
    private Integer totalPrice;

    @Column
    private Long shopId;

    @Column
    private Long memberId;

    @Column(name="payment_method")
    private String paymentMethod;   //결제 방법

    @Column(name="request")
    private String request; //요청사항

    public Order(String menuName, Integer amount, Integer totalPrice, Long shopId, String request, String paymentMethod, Long memberId){
        this.menuName = menuName;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.shopId = shopId;
        this.request = request;
        this.paymentMethod = paymentMethod;
        this.memberId = memberId;
    }
}
