package com.sprta.deliveryproject.dto;

import com.sprta.deliveryproject.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderResponseDto {
    private Long orderId;
    private Integer totalPrice;
    private Long shopId;
    private Long memberId;
    private String paymentMethod;
    private String request;
    private LocalDateTime createdAt;

    public OrderResponseDto(Order order){
        this.orderId = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.shopId = order.getShop().getId();
        this.memberId = order.getMember().getId();
        this.paymentMethod = order.getPaymentMethod();
        this.request = order.getRequest();
        this.createdAt = order.getCreatedAt();

    }
}
