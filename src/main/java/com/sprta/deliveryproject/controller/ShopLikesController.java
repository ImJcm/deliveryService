package com.sprta.deliveryproject.controller;


// 후에 shopController 에 합쳐질 수 있다.

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.security.UserDetailsImpl;
import com.sprta.deliveryproject.service.ShopLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop")
public class ShopLikesController {
    private final ShopLikesService shopLikesService;

    //찜하기
    @PostMapping("/{shop_id}/likes")
    public ResponseEntity<ApiResponseDto> createShopLikes(@PathVariable(value = "shop_id") Long shopId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        shopLikesService.createShopLikes(shopId, userDetails.getMember());
        return ResponseEntity.ok().body(new ApiResponseDto("가게를 찜했습니다.", HttpStatus.OK.value()));
    }

    //찜하기 취소
    @DeleteMapping("likes/{shop_like_id}")
    public ResponseEntity<ApiResponseDto> deleteShopLikes(@PathVariable(value = "shop_like_id") Long shopLikeId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        shopLikesService.deleteShopLikes(shopLikeId, userDetails.getMember());
        return ResponseEntity.ok().body(new ApiResponseDto("가게 찜을 취소했습니다.", HttpStatus.OK.value()));
    }


    //특정 멤버가 찜한(좋아요) 가게 조회 (관리자 기능)
    @GetMapping("/member/{member_id}/likes")
    public ResponseEntity<ApiResponseDto> getMemberShopLikes(@PathVariable(value = "member_id") Long memberId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "특정 멤버가 찜한 가게 목록 조회 성공.", shopLikesService.getMemberlikeshop(memberId, userDetails.getMember())));
    }
}
