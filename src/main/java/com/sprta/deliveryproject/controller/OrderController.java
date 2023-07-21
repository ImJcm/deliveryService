package com.sprta.deliveryproject.controller;


import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.OrderRequestDto;
import com.sprta.deliveryproject.dto.OrderResponseDto;
import com.sprta.deliveryproject.security.UserDetailsImpl;
import com.sprta.deliveryproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<ApiResponseDto> createOrder(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody OrderRequestDto orderRequestDto){
        orderService.createOrder(userDetails.getMember(), orderRequestDto);
        return ResponseEntity.ok().body(new ApiResponseDto("주문이 완료되었습니다.", HttpStatus.OK.value()));
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> showOrder(@AuthenticationPrincipal UserDetailsImpl userDetails){
        List<OrderResponseDto> orderList = orderService.showOrder(userDetails.getMember());
        return orderList;
    }




}
