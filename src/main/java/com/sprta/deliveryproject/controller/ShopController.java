package com.sprta.deliveryproject.controller;

import com.sprta.deliveryproject.dto.ShopResponseDto;
import com.sprta.deliveryproject.security.UserDetailsImpl;
import com.sprta.deliveryproject.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/shop")
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/member/{member_id}/likes")
    @ResponseBody
    public List<ShopResponseDto> getMemberlikeshop(@PathVariable Long member_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return shopService.getMemberlikeshop(member_id, userDetails.getMember());
    }
}
