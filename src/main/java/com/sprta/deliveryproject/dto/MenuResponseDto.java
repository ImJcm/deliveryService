package com.sprta.deliveryproject.dto;

import com.sprta.deliveryproject.entity.Category;
import com.sprta.deliveryproject.entity.Member;
import com.sprta.deliveryproject.entity.Menu;
import com.sprta.deliveryproject.entity.Shop;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MenuResponseDto {
    private Long menuId;
    private String introduce;
    private String menuname;
    private Integer price;
    private String shopname;
    private Long shopId;
    private String imgSrc;
    private String shopAddress;

    public MenuResponseDto(Menu menu) {
        this.menuId = menu.getId();
        this.introduce = menu.getIntroduce();
        this.menuname = menu.getMenuname();
        this.price = menu.getPrice();
        this.shopname = menu.getShop().getShopname();
        this.shopId = menu.getShop().getId();
        this.imgSrc = menu.getImgSrc();
        this.shopAddress = menu.getShop().getAddress();
    }
}
