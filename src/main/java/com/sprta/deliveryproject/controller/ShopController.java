package com.sprta.deliveryproject.controller;

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.ShopResponseDto;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.security.UserDetailsImpl;
import com.sprta.deliveryproject.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopController
{
    private ShopService shopService;

    @GetMapping("/shops/category/{id}")
    public ResponseEntity<ApiResponseDto> getCategoryShops(@PathVariable Long id) {
        return shopService.getCategoryShops(id);
    }
    @GetMapping("/shops/{id}")
    public ResponseEntity<ApiResponseDto> getShops(@PathVariable Long id) {
        return shopService.getDetailShops(id);
    }

    @GetMapping("/member/{member_id}/likes")
    public List<ShopResponseDto> getMemberlikeshop(@PathVariable Long member_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return shopService.getMemberlikeshop(member_id, userDetails.getMember());
    }
}
