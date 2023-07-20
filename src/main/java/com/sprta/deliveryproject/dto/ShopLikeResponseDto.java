package com.sprta.deliveryproject.dto;

import com.sprta.deliveryproject.entity.Category;
import com.sprta.deliveryproject.entity.ShopLike;
import lombok.Getter;

@Getter
public class ShopLikeResponseDto {
    private Long id;
    private String shopname;        //가게이름
    private Integer review_count;   //리뷰 개수
    private Integer like_count;     //좋아요 개수
    private String address;          //가게주소
    private String phone_number;    //전화번호
    private Category category;      //카테고리

    public ShopLikeResponseDto(ShopLike shopLike) {
        this.id = shopLike.getId();
        this.shopname = shopLike.getShop().getShopname();
        this.review_count = shopLike.getShop().getReview_count();
        this.address = shopLike.getShop().getAddress();
        this.phone_number = shopLike.getShop().getPhone_number();
        this.category = shopLike.getShop().getCategory();
        this.like_count = shopLike.getShop().getLikeList().size();
    }
}
