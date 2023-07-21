package com.sprta.deliveryproject.controller;

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.CartRequestDto;
import com.sprta.deliveryproject.security.UserDetailsImpl;
import com.sprta.deliveryproject.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<ApiResponseDto> addCart(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CartRequestDto cartRequestDto) {
        try {
            cartService.addCart(userDetails.getMember(), cartRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.ok().body(new ApiResponseDto("장바구니에 메뉴를 담았습니다", HttpStatus.OK.value()));
    }


}
