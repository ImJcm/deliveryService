package com.sprta.deliveryproject.dto;

import com.sprta.deliveryproject.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderResponseDto {
    private Long orderId;           //주문 번호
    private Integer totalPrice;     //총 주문금액
    private Long shopId;            //가게
    private String shopname;        //가게 이름
    private String image_src;       //가게 이미지
    private Long memberId;          //주문자
    private String paymentMethod;   //결재 방법
    private String request;         //요청 사항
    private LocalDateTime createdAt;    // 주문일자
    private boolean isReviewed;     //리뷰 여부

    public OrderResponseDto(Order order){
        this.orderId = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.shopId = order.getShop().getId();
        this.shopname = order.getShop().getShopname();
        this.memberId = order.getMember().getId();
        this.paymentMethod = order.getPaymentMethod();
        this.request = order.getRequest();
        this.createdAt = order.getCreatedAt();
        this.image_src = order.getShop().getImage_src();
        this.isReviewed = order.getIsReviewed();
    }
}
