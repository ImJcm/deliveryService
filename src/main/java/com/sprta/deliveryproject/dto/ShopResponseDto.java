package com.sprta.deliveryproject.dto;

import com.sprta.deliveryproject.entity.Category;
import com.sprta.deliveryproject.entity.Review;
import com.sprta.deliveryproject.entity.Shop;

import lombok.Getter;

import java.util.List;

@Getter
public class ShopResponseDto {

    private Long id;
    String shopname;    // 가게이름
    Integer review_count; //리뷰 수
    String address; // 주소
    String phone_number; // 전화번호
    String username;
    Category category; // 가게 분류
    private Integer likes;  //좋아요 개수

    String image_src;   //가게 이미지

    //private List<Review> reviewList;    //리뷰
    public ShopResponseDto(Shop shop) {
        this.id = shop.getId();
        this.shopname = shop.getShopname();
        this.review_count = shop.getReview_count();
        this.address = shop.getAddress();
        this.phone_number = shop.getPhone_number();
        this.category = shop.getCategory();
        //this.reviewList = shop.getReviewList();
        this.likes = shop.getLikeList().size();
        this.username = shop.getUsername();
        this.image_src = shop.getImage_src();
    }

}
