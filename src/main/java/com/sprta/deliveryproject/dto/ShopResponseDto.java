package com.sprta.deliveryproject.dto;

import com.sprta.deliveryproject.entity.Category;
import com.sprta.deliveryproject.entity.Review;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.entity.ShopLike;
import lombok.Getter;

import java.util.List;

@Getter
public class ShopResponseDto {
    private Long id;
    String shopname;
    Integer review_count;
    String adress;
    String phone_number;
    Category category;
    List<Review> reviewList;
    List<ShopLike> likeList;

    public ShopResponseDto(Shop shop){
        this.id = shop.getId();
        this.shopname = shop.getShopname();
        this.review_count = shop.getReview_count();
        this.adress = shop.getAdress();
        this.phone_number = shop.getPhone_number();
        this.category = shop.getCategory();
        this.reviewList = shop.getReviewList();
        this.likeList = shop.getLikeList();
    }
}