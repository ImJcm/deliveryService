package com.sprta.deliveryproject.dto;

import com.sprta.deliveryproject.entity.*;

public class CartsResponseDto {
    private Integer amount;
    private String shopname;
    private String menuname;
    private Long order_id;

    public CartsResponseDto(Carts carts){
        this.shopname = carts.getShop().getShopname();
        this.menuname = carts.getMenu().getMenuname();
        this.amount = carts.getAmount();
        this.order_id = carts.getOrder().getId();
    }
}
