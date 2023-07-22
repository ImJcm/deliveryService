package com.sprta.deliveryproject.dto;

import lombok.Getter;

@Getter
public class CartRequestDto {
    private Long menuId;
    private Long shopId;
    private Long memberId;
    private Long orderId = null;
    private Integer amount;
}
