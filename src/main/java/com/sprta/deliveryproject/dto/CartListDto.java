package com.sprta.deliveryproject.dto;

import com.sprta.deliveryproject.entity.CartList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartListDto {
    private Long shopId;
    private String shopName;
    List<CartDto> cartDto;

    public CartListDto(List<CartDto> cartList){
        this.cartDto = cartList;
    }


    public CartListDto(Long shopId, String shopName, List<CartDto> newCart) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.cartDto = newCart;
    }

    public CartListDto(CartList cartList) {
    }
}
