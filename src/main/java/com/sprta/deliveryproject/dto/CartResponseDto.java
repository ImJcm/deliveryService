package com.sprta.deliveryproject.dto;

import com.sprta.deliveryproject.entity.Cart;
import lombok.Getter;

@Getter
public class CartResponseDto {
    private Integer amount;
    private String shopname;
    private String menuname;
    private Long order_id;
    private Integer price;

    public CartResponseDto(Cart cart){
        this.shopname = cart.getShop().getShopname();
        this.menuname = cart.getMenu().getMenuname();
        this.amount = cart.getAmount();
        this.order_id = cart.getOrder() == null ? null : cart.getOrder().getId();
        this.price = cart.getMenu().getPrice();
    }
}
