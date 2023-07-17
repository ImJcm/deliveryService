package com.sprta.deliveryproject.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sprta.deliveryproject.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;
//review Request 땐 contents 만 사용
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDto extends ApiResponseDto {
    private Long id;
    private String contents;
    private LocalDateTime createdAt;
    //review response에서 가게이름과 리뷰작성자의 닉네임을 보여준다.
    private String shopName;
    private String profileName;


    public ReviewDto(Review review) {
        this.id = review.getId();
        this.contents = review.getContent();
        this.createdAt = review.getCreatedAt();
        this.shopName = review.getShop().getShopname();
        this.profileName = review.getMember().getProfilename();
    }

}
