package com.sprta.deliveryproject.dto;

import lombok.Getter;

@Getter
public class CartDto {
    private String menuname;
    private Integer price;
    private Integer amount;
    private Integer totalPrice;

}
