package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.CartRequestDto;
import com.sprta.deliveryproject.entity.Cart;
import com.sprta.deliveryproject.entity.CartList;
import com.sprta.deliveryproject.entity.Member;
import com.sprta.deliveryproject.entity.Menu;
import com.sprta.deliveryproject.repository.CartListRepository;
import com.sprta.deliveryproject.repository.CartRepository;
import com.sprta.deliveryproject.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartListRepository cartListRepository;
    private final CartRepository cartRepository;
    private final MenuRepository menuRepository;

    //장바구니에 메뉴 담기

    @Transactional
    public void addCart(Member member, CartRequestDto cartRequestDto) { //CartList -> 주문수량, 토탈가격, cartID, menuID
        // 메뉴 ID 로 메뉴 객체 가져오기
        Long menuId = cartRequestDto.getMenuId();
        Menu menu = findMenu(menuId);

        // 변수들 정리
        Integer amount = cartRequestDto.getAmount();
        Integer price = menu.getPrice();
        Integer totalPrice = addPrice(price, amount);

        Long shopId = menu.getShop().getId();



        // user 의 장바구니가 없으면 만들고 있으면 장바구니에 아이템을 담는다
        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartList cartList = new CartList(amount, totalPrice, shopId, menu.getMenuname());
        cartList.setCart(cart);
        cartList.setMenu(menu);

        CartList nullCheckCartList = cartListRepository.findTopByCartId(cart.getId()).orElse(null);
        List<CartList> menuCheckCartList = cartListRepository.findByCartId(cart.getId());

        if(nullCheckCartList == null) {//장바구니가 비어있는 상태인지
            cartListRepository.save(cartList);
        } else {
            if (nullCheckCartList.getShopId().equals(cartList.getShopId())){//같은 가게 메뉴를 담았는지
                if(cartListRepository.findByMenuId(menuId).isPresent()){
                    CartList upCartList = cartListRepository.findByMenuName(menu.getMenuname());
                    Integer upAmount = upCartList.getAmount() + cartList.getAmount();
                    Integer upTotalPrice = upCartList.getTotalPrice() + cartList.getTotalPrice();
                    upCartList.setAmount(upAmount);
                    upCartList.setTotalPrice(upTotalPrice);
                } else {
                    cartListRepository.save(cartList);
                }
            } else {
                throw new IllegalArgumentException("같은 가게 메뉴만 담을 수 있습니다");
            }
        }
    }

//    //장바구니에 담은 메뉴 보여주기
//    public List<CartListDto> showCart(Member member) {
//        Long memberId = member.getId();
//        Cart cart = cartRepository.findById(memberId).orElseThrow(
//                () -> new IllegalArgumentException("장바구니에 담은 메뉴가 없습니다")
//        );
//
//        List<CartListDto> cartList = cartListRepository.findById(cart.getId())
//                .stream().map(CartListDto::new)
//                .collect(Collectors.toList());
//        return cartList;
//    }

    //장바구니 메뉴 전체삭제
//    public void deleteMenuFromCart(Cart cart) {
//        Long cartId = cart.getId();
//        List<CartList> cartList = cartListRepository.findByCartId(cartId);
//
//        cartListRepository.deleteAll(cartList);
//    }

    public Integer addPrice(Integer price, Integer amount) {
        return price * amount;
    }

    public Menu findMenu(long id) {
        return menuRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지않는메뉴")
        );
    }
}
