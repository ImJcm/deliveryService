package com.sprta.deliveryproject.controller;

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ShopController
{
    private ShopService shopService;

    public ShopController(ShopService shopService){
        this.shopService = shopService;
    }
    @GetMapping("/api/{category_id}/shops")
    public ResponseEntity<ApiResponseDto> getCategoryShops(@PathVariable Long category_id) {
        return shopService.getCategoryShops(category_id);
    }
    @GetMapping("/api/shops/{shop_id}")
    public ResponseEntity<ApiResponseDto> getShops(@PathVariable Long shop_id) {
        return shopService.getShops(shop_id);
    }


}
