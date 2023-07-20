package com.sprta.deliveryproject.controller;

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopController{
    private final ShopService shopService;

    @GetMapping("/shops/category/{id}")     //카테고리 별 가게 조회
    public ResponseEntity<ApiResponseDto> getCategoryShops(@PathVariable Long id) {
        return shopService.getCategoryShops(id);
    }
    @GetMapping("/shops/{id}")          //특정가게 조회
    public ResponseEntity<ApiResponseDto> getShops(@PathVariable Long id) {
        return shopService.getDetailShops(id);
    }
    @GetMapping("/shops/{id}/menu")     //특정가게 메뉴조회
    public ResponseEntity<ApiResponseDto> getMenus(@PathVariable Long id){   //특정가게 메뉴조회
        return shopService.getMenus(id);
    }

}
