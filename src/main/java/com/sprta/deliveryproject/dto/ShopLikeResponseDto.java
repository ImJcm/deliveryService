package com.sprta.deliveryproject.dto;

import com.sprta.deliveryproject.entity.Member;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.entity.ShopLike;
import lombok.Getter;

@Getter
public class ShopLikeResponseDto {
    private Long id;
    private Shop shop;
    private Member member;

    public ShopLikeResponseDto(ShopLike shopLike) {
        this.id = shopLike.getId();
        this.shop = shopLike.getShop();
        this.member = shopLike.getMember();
    }
}
