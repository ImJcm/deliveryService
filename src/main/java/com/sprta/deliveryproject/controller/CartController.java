package com.sprta.deliveryproject.controller;

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.CartDto;
import com.sprta.deliveryproject.dto.CartListDto;
import com.sprta.deliveryproject.security.UserDetailsImpl;
import com.sprta.deliveryproject.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<ApiResponseDto> addCart(@AuthenticationPrincipal UserDetailsImpl userDetails, Long shopId, String shopName, @RequestBody CartDto cartDto) {
        try {
            cartService.addCart(userDetails.getMember(), shopId, shopName, cartDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.ok().body(new ApiResponseDto("장바구니에 메뉴를 담았습니다", HttpStatus.OK.value()));
    }

    @GetMapping("/cart")
    public ResponseEntity<List<CartListDto>> showCart(@AuthenticationPrincipal UserDetailsImpl userDetails){
        List<CartListDto> cartList = cartService.showCart(userDetails.getMember());

        if(cartList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(cartList);
        }
    }

    @DeleteMapping("/cart/{menuId}")
    public ResponseEntity<String> deleteMenuFromCart(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long menuId){
        cartService.deleteMenuFromCart(userDetails.getMember(), menuId);
        return ResponseEntity.ok("장바구니에 메뉴를 삭제했습니다.");
    }
}
