package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.ShopResponseDto;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.repository.ShopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    private ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository){
        this.shopRepository = shopRepository;
    }


    public ResponseEntity<ApiResponseDto> getCategoryShops(Long categoryId) {
        List<Shop> postList = shopRepository.findAllByCategory_IdOrderByModifiedAtDesc(categoryId);

        List<ShopResponseDto> newPostList = postList.stream().map(ShopResponseDto::new).toList();

        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(),"카테고리 조회",newPostList));
    }

    public ResponseEntity<ApiResponseDto> getShops(Long shopId) {
        Shop shop = FindShop(shopId);

        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(), "특정 가게 조회", shop));
    }

    public Shop FindShop(Long id){
        return shopRepository.findById(id).orElseThrow(() ->
                        new IllegalArgumentException("해당 가게는 존재하지 않습니다. 다시 시도해주세요.")
                );
    }
}
