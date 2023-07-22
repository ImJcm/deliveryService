package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.CartRequestDto;
import com.sprta.deliveryproject.dto.CartResponseDto;
import com.sprta.deliveryproject.entity.*;
import com.sprta.deliveryproject.repository.CartRepository;
import com.sprta.deliveryproject.repository.MenuRepository;
import com.sprta.deliveryproject.repository.OrderRepository;
import com.sprta.deliveryproject.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

        List<Carts> CartsList = cartsRepository.findAllByMemberIdAndOrderIdIsNull(member.getId());

//        if (CartsList.isEmpty()) { //담은 메뉴가 없으면 바로 장바구니에 담는다
//            cartsRepository.save(carts);
//        } else { //담은 메뉴가 있다면
//            if (Objects.equals(CartsList.get(0).getShop().getId(), shopId)) { //담겨진 메뉴의 shopID 와 비교
//                for (Carts carts1 : CartsList) {//shopID가 같으면. .?
//                    if (carts1.getMenu().getMenuname().equals(menuName)) {//메뉴네임 비교해서 같은 메뉴 있으면 주문수량만 추가
//                        carts1.setAmount(carts1.getAmount() + amount);
//                    }
//                }
//                if(){
//                    cartsRepository.save(carts);
//                }
//            } else {
//                throw new IllegalArgumentException("같은 가게 메뉴만 담을 수 있습니다");
//            }
//        }


    }

    /* shop_id에 해당하는 가게에서 메뉴를 담은 cart 조회 */
    public List<CartResponseDto> getMemberCart(Long shop_id, Member member) {
        List<CartResponseDto> cartResponseDtoList = cartRepository.findAllByMemberIdAndShopIdAndOrderIdIsNull(member.getId(),shop_id)
                .stream()
                .map(CartResponseDto::new)
                .toList();

        return cartResponseDtoList;
    }

    /* order_id에 해당하는 menu 내역 조회 */
    public List<CartResponseDto> getOrderCart(Long order_id, Member member) {
        List<CartResponseDto> cartResponseDtoList = cartRepository.findAllByMemberIdAndOrderId(member.getId(),order_id)
                .stream()
                .map(CartResponseDto::new)
                .toList();

        return cartResponseDtoList;
    }
}
