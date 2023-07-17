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


    public ResponseEntity<ApiResponseDto> getCategoryPosts(Long categoryId) {
        List<Shop> postList = shopRepository.findAllByCategory_IdOrderByModifiedAtDesc(categoryId);

        List<ShopResponseDto> newPostList = postList.stream().map(ShopResponseDto::new).toList();

        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(),"카테고리 조회",newPostList));
    }
}
