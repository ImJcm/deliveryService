package com.sprta.deliveryproject.controller;

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.MenuRequestDto;
import com.sprta.deliveryproject.security.UserDetailsImpl;
import com.sprta.deliveryproject.security.UserDetailsServiceImpl;
import com.sprta.deliveryproject.service.BackOfficeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BackOfficeController {

    private BackOfficeService backOfficeService;

    public BackOfficeController(BackOfficeService backOfficeService){
        this.backOfficeService = backOfficeService;
    }

    @PostMapping("/shops/{id}/menus")       //특정가게 메뉴 추가(해당 가게 주인만 가능)
    public ResponseEntity<ApiResponseDto> AddMenu(@PathVariable Long id, @RequestBody MenuRequestDto menuRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        return backOfficeService.AddMenu(id,menuRequestDto,userDetails);

    }
    @PutMapping("/shops/{shop_id}/menus/{menu_id}")
    public String UpdateMenu(@PathVariable("shop_id") Long shop_id, @PathVariable("menu_id") Long menu_id,
                             @RequestBody MenuRequestDto menuRequestDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails){
        return backOfficeService.updateMenu(shop_id, menu_id, menuRequestDto,userDetails);
    }
    @GetMapping("/member/shops/menus")
    public ResponseEntity<ApiResponseDto> getMembersShopMenu(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return backOfficeService.getMembersShopMenu(userDetails);
    }

    @DeleteMapping("/shops/{shop_id}/menus/{menu_id}")
    public String DeleteMenu(@PathVariable("shop_id") Long shop_id, @PathVariable("menu_id") Long menu_id,
                             @AuthenticationPrincipal UserDetailsImpl userDetails){
        return backOfficeService.DeleteMenu(shop_id, menu_id ,userDetails);
    }
}
