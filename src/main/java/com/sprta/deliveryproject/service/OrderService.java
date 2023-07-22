package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.CartResponseDto;
import com.sprta.deliveryproject.dto.OrderRequestDto;
import com.sprta.deliveryproject.dto.OrderResponseDto;
import com.sprta.deliveryproject.entity.*;
import com.sprta.deliveryproject.repository.CartRepository;
import com.sprta.deliveryproject.repository.MenuRepository;
import com.sprta.deliveryproject.repository.OrderRepository;
import com.sprta.deliveryproject.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Slf4j(topic = "Order Controller")
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final CartRepository cartRepository;
    private final ShopRepository shopRepository;

    //주문하기
    @Transactional
    public void createOrder(Member member, OrderRequestDto orderRequestDto) {
        String request = orderRequestDto.getRequest();
        String paymentMethod = orderRequestDto.getPaymentMethod();
        Integer totalPrice = 0;
        Long shopId = orderRequestDto.getShopId();

        List<Cart> CartList = cartRepository.findAllByMemberIdAndShopIdAndOrderIdIsNull(member.getId(), shopId);

        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));
        Order order = new Order();
        for (Cart cart : CartList) {
            totalPrice += cart.getMenu().getPrice() * cart.getAmount();
            cart.setOrder(order);
        }

        order.setOrder(paymentMethod, request, totalPrice, shop, member);
        orderRepository.save(order);
    }

    //나의 주문목록
    public List<OrderResponseDto> showOrder(Member member) {
        Long memberId = member.getId();
        List<OrderResponseDto> orderResponseDtoList = orderRepository.findAllByMemberId(memberId)
                .stream()
                .map(OrderResponseDto::new)
                .toList();

        return orderResponseDtoList;
    }

    //주문 취소
    public void deleteOrder(Member member, Long id) {
        Order order = findOrder(id);

        if (!order.getMember().equals(member)){
            throw new RejectedExecutionException();
        }

        orderRepository.delete(order);
    }

    private Order findOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 주문이거나 이미 취소된 주문입니다.")
        );
    }

    public Menu findMenu(long id) {
        return menuRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 메뉴입니다.")
        );
    }


}
