package com.sprta.deliveryproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private Long shopId;
    private String request;
    private String paymentMethod;
}
