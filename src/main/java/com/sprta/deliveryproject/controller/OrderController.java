package com.sprta.deliveryproject.controller;


import com.sprta.deliveryproject.dto.CartListDto;
import com.sprta.deliveryproject.dto.OrderRequestDto;
import com.sprta.deliveryproject.security.UserDetailsImpl;
import com.sprta.deliveryproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public void createOrder(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody OrderRequestDto orderRequestDto){
        orderService.createOrder(userDetails.getMember(), orderRequestDto);
    }




}
