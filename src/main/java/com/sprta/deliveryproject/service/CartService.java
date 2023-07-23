package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.CartRequestDto;
import com.sprta.deliveryproject.dto.CartResponseDto;
import com.sprta.deliveryproject.entity.*;
import com.sprta.deliveryproject.repository.CartRepository;
import com.sprta.deliveryproject.repository.MenuRepository;
import com.sprta.deliveryproject.repository.OrderRepository;
import com.sprta.deliveryproject.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MenuRepository menuRepository;
    private final ShopRepository shopRepository;
    private final OrderRepository orderRepository;

    //장바구니에 메뉴 담기
    @Transactional
    public void addCart(Member member, CartRequestDto cartRequestDto) {
        Long menuId = cartRequestDto.getMenuId();
        Long shopId = cartRequestDto.getShopId();
        //Long memberId = member.getId();         //cartRequestDto.getMemberId();
        Long orderId = cartRequestDto.getOrderId();
        Integer amount = cartRequestDto.getAmount();

        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메뉴입니다."));
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));
        Order order = orderId == null ? null : orderRepository.findById(orderId).orElse(null);
        Cart cart = new Cart(amount, member, shop, menu, order);  //메뉴 수량, 멤버, 가게, 메뉴, 오더번호(초기 값 null)

        cartRepository.save(cart);
    }

    /* shop_id에 해당하는 가게에서 메뉴를 담은 cart 조회 */
    public List<CartResponseDto> getMemberCart(Long shop_id, Member member) {
        List<CartResponseDto> cartResponseDtoList = cartRepository.findAllByMemberIdAndShopIdAndOrderIdIsNull(member.getId(),shop_id)
                .stream()
                .map(CartResponseDto::new)
                .toList();

        return cartResponseDtoList;
    }

    /* order_id에 해당하는 Order의 cart 내역 조회 */
    public List<CartResponseDto> getOrderCart(Long order_id, Member member) {
        List<CartResponseDto> cartResponseDtoList = cartRepository.findAllByMemberIdAndOrderId(member.getId(),order_id)
                .stream()
                .map(CartResponseDto::new)
                .toList();

        return cartResponseDtoList;
    }
    //로그인한 유저 관계 없이 orderId에 해당하는 cart 내역 조회
    public List<CartResponseDto> getOrderCarts(Long order_id) {
        List<CartResponseDto> cartResponseDtoList = cartRepository.findByOrderId(order_id)
                .stream()
                .map(CartResponseDto::new)
                .toList();

        return cartResponseDtoList;
    }
}
