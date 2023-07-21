package com.sprta.deliveryproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cart_list")
public class CartList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer amount;

    @Column
    private Integer totalPrice;

    @Column
    private Long shopId;

    @Column
    private String menuName;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "menu_id") // 메뉴정보 (메뉴이름, 가격, 가게ID)
    private Menu menu;

    public CartList(Integer amount, Integer totalPrice, Long shopId, String menuName){
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.shopId = shopId;
        this.menuName = menuName;
    }
}
