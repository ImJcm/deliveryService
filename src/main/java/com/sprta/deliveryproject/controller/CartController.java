package com.sprta.deliveryproject.controller;

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.CartRequestDto;
import com.sprta.deliveryproject.dto.CartResponseDto;
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
    public ResponseEntity<ApiResponseDto> addCart(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CartRequestDto cartRequestDto) {
        try {
            cartService.addCart(userDetails.getMember(), cartRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.ok().body(new ApiResponseDto("장바구니에 메뉴를 담았습니다", HttpStatus.OK.value()));
    }

    /* shop_id에 해당하는 cart 내역 조회 */
    @GetMapping("/carts/shop/{shop_id}")
    public List<CartResponseDto> getMemberCart(@PathVariable Long shop_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cartService.getMemberCart(shop_id, userDetails.getMember());
    }

    /* order_id에 해당하는 cart 내역 조회 */
    @GetMapping("/carts/order/{order_id}")
    public List<CartResponseDto> getOrderCart(@PathVariable Long order_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cartService.getOrderCart(order_id,userDetails.getMember());
    }
    @GetMapping("/carts/orders/{order_id}")
    public List<CartResponseDto> getOrderCarts(@PathVariable Long order_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cartService.getOrderCarts(order_id);
    }

}
