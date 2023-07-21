package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="orders")
public class Order extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orders_id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name="payment_method")
    private String paymentMethod;

    @Column(name="request")
    private String request;

    @Column(name = "totalPrice")
    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;




    public void setOrder(String paymentMethod, String request, Integer totalPrice, Shop shop, Member member){
        this.paymentMethod = paymentMethod;
        this.request = request;
        this.totalPrice = totalPrice;
        this.shop = shop;
        this.member = member;
    }
}
