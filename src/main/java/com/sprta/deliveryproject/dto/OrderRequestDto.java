package com.sprta.deliveryproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private Long orderId;
    private Long memberId;
    private Long shopId;
    private String requests;
    private String paymentMethod;
//    private Integer 수량;
}
