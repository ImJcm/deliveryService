package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.CartListDto;
import com.sprta.deliveryproject.dto.OrderRequestDto;
import com.sprta.deliveryproject.entity.Cart;
import com.sprta.deliveryproject.entity.CartList;
import com.sprta.deliveryproject.entity.Member;
import com.sprta.deliveryproject.repository.CartListRepository;
import com.sprta.deliveryproject.repository.CartRepository;
import com.sprta.deliveryproject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService{

    private final OrderRepository orderRepository;
    private final CartListRepository cartListRepository;
    private final CartRepository cartRepository;

    public void createOrder(Member member, OrderRequestDto orderRequestDto) {
        Long memberId = orderRequestDto.getMemberId();
        Long shopId = orderRequestDto.getShopId();
        String request = orderRequestDto.getRequests();
        String paymentMethod = orderRequestDto.getPaymentMethod();

    }
}
