package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cart_list")
public class CartList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menuname")
    private String menuName;

    @Column(name = "price")
    private Integer price;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "totalPrice")
    private Integer totalPrice;

    @Column(name = "shopname")
    private String shopname;

    @Column(name = "shopId")
    private Long shopId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;


    public CartList(String menuName, Integer price, Integer amount, Integer totalPrice, Long shopId, String shopname) {
        this.menuName = menuName;
        this.price = price;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.shopId = shopId;
        this.shopname = shopname;

    }
}
