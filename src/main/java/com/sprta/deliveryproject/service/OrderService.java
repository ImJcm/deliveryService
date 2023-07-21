package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.OrderRequestDto;
import com.sprta.deliveryproject.dto.OrderResponseDto;
import com.sprta.deliveryproject.entity.*;
import com.sprta.deliveryproject.repository.CartListRepository;
import com.sprta.deliveryproject.repository.CartRepository;
import com.sprta.deliveryproject.repository.MenuRepository;
import com.sprta.deliveryproject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartListRepository cartListRepository;
    private final MenuRepository menuRepository;
    private final CartRepository cartRepository;

    public void createOrder(Member member, OrderRequestDto orderRequestDto) {
        String request = orderRequestDto.getRequests();
        String paymentMethod = orderRequestDto.getPaymentMethod();

        Long memberId = member.getId();
        Cart cart = cartRepository.findByMemberId(memberId);
        List<CartList> cartLists = cartListRepository.findByCartId(cart.getId());

        for (CartList cartList : cartLists) {
            Menu menu = findMenu(cartList.getMenu().getId());
            String menuname = menu.getMenuname();
            Integer amount = cartList.getAmount();
            Integer totalPrice = cartList.getTotalPrice();
            Long shopId = menu.getShop().getId();
            orderRepository.save(new Order(menuname, amount, totalPrice, shopId, request, paymentMethod, member.getId()));
        }
        deleteMenuFromCart(cart);
    }

    public List<OrderResponseDto> showOrder(Member member) {
        Long memberId = member.getId();
        List<OrderResponseDto> orderList = orderRepository.findAllByMemberId(memberId).stream().map(OrderResponseDto::new).toList();

        return orderList;
    }


    public Menu findMenu(long id) {
        return menuRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지않는메뉴")
        );
    }

    public void deleteMenuFromCart(Cart cart) {
        Long cartId = cart.getId();
        List<CartList> cartList = cartListRepository.findByCartId(cartId);

        cartListRepository.deleteAll(cartList);
    }


}
