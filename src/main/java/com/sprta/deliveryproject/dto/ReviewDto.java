package com.sprta.deliveryproject.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sprta.deliveryproject.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;

//review Request 땐 contents 만 사용
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDto {
    private final Long id;
    private final String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime modifiedAt;
    //review 의 shop 과 member 정보를 통해 response 에서 가게이름과 리뷰작성자의 닉네임을 보여준다.
    private final String shopName;
    private final String profileName;

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.contents = review.getContent();
        this.createdAt = review.getCreatedAt();
        this.modifiedAt = review.getModifiedAt();
        this.shopName = review.getShop().getShopname();
        this.profileName = review.getMember().getProfilename();
    }

}
