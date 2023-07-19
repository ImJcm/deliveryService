package com.sprta.deliveryproject.controller;


// 후에 shopController 에 합쳐질 수 있다.

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.ShopLikeResponseDto;
import com.sprta.deliveryproject.dto.ShopResponseDto;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.security.UserDetailsImpl;
import com.sprta.deliveryproject.service.ShopLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop")
public class ShopLikesController {
    private final ShopLikesService shopLikesService;
    
    //찜하기
    @PostMapping("/{shopId}/likes")
    public ResponseEntity<ApiResponseDto> createShopLikes(@PathVariable Long shopId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        shopLikesService.createShopLikes(shopId,userDetails.getMember());
        return ResponseEntity.ok().body(new ApiResponseDto("해당 가게를 찜했습니다.",HttpStatus.OK.value()));
    }

    //찜하기 취소
    @DeleteMapping("likes/{id}")
    public ResponseEntity<ApiResponseDto> deleteShopLikes(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails){
        shopLikesService.deleteShopLikes(id,userDetails.getMember());
        return ResponseEntity.ok().body(new ApiResponseDto("해당 가게 찜을 취소했습니다.", HttpStatus.OK.value()));
    }


    //특정 멤버가 찜한(좋아요) 가게 조회
    @GetMapping("/member/{member_id}/likes")
    public List<ShopLikeResponseDto> getMemberlikeshop(@PathVariable Long member_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return shopLikesService.getMemberlikeshop(member_id, userDetails.getMember());
    }
}
