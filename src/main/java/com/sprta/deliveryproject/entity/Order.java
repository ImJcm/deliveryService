package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name="orders")
public class Order extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="payment_method")
    private String paymentmethod;   //결제 방법

    @Column(name="request")
    private String request; //요청사항

}
