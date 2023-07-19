package com.sprta.deliveryproject.dto;

import lombok.Getter;

@Getter
public class CartRequestDto {
    private String menuname;
    private Integer price;
    private Integer amount;
    private Long shopId;
    private String shopname;

}
