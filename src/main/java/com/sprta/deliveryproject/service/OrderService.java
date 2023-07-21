package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.OrderRequestDto;
import com.sprta.deliveryproject.dto.OrderResponseDto;
import com.sprta.deliveryproject.entity.*;
import com.sprta.deliveryproject.repository.CartsRepository;
import com.sprta.deliveryproject.repository.MenuRepository;
import com.sprta.deliveryproject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final CartsRepository cartsRepository;

    //주문하기
    @Transactional
    public void createOrder(Member member, OrderRequestDto orderRequestDto) {
        String request = orderRequestDto.getRequests();
        String paymentMethod = orderRequestDto.getPaymentMethod();
        Integer totalPrice = 0;

        List<Carts> CartsList = cartsRepository.findAllByMemberIdAndOrderIdIsNull(member.getId()); // 자신이 주문한 메뉴 && 주문번호가 null 인 메뉴 리스트를 뽑아옴
        Order order = new Order(); //주문 하나 생성

        for (Carts carts : CartsList) { //장바구니 목록에 주문번호가 없는 메뉴에 주문번호 부여 (하나의 주문으로 통합)
            totalPrice = totalPrice + carts.getMenu().getPrice();
            carts.setOrder(order);
        }

//        order.setOrder(paymentMethod, request, totalPrice, shop, member); // shop을 어케가져오지?
        orderRepository.save(order);
    }

    //나의 주문목록
    public List<OrderResponseDto> showOrder(Member member) {
        Long memberId = member.getId();
        List<OrderResponseDto> orderList = orderRepository.findAllByMemberId(memberId).stream().map(OrderResponseDto::new)
                .sorted(Comparator.comparing(OrderResponseDto::getCreatedAt).reversed()).toList();

        return orderList;
    }


    public Menu findMenu(long id) {
        return menuRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지않는메뉴")
        );
    }




}
