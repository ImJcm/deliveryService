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
    @GetMapping("/shops/category/{id}")
    public ResponseEntity<ApiResponseDto> getCategoryShops(@PathVariable Long id) {
        return shopService.getCategoryShops(id);
    }
    @GetMapping("/shops/{id}")
    public ResponseEntity<ApiResponseDto> getShops(@PathVariable Long id) {
        return shopService.getDetailShops(id);
    }


}
