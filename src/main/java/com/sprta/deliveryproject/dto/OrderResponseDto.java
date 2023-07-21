package com.sprta.deliveryproject.dto;

import com.sprta.deliveryproject.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private String menuName;
    private Integer amount;
    private Integer totalPrice;
    private Long shopId;
    private Long memberId;
    private String paymentMethod;
    private String request;
    private LocalDateTime createdAt;

    public OrderResponseDto(Order order){
        this.id = order.getId();
        this.menuName = order.getMenuName();
        this.amount = order.getAmount();
        this.totalPrice = order.getTotalPrice();
        this.shopId = order.getShopId();
        this.memberId = order.getMemberId();
        this.paymentMethod = order.getPaymentMethod();
        this.request = order.getRequest();
        this.createdAt = order.getCreatedAt();

    }
}
