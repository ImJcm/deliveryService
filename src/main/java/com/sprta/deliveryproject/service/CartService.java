package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.CartDto;
import com.sprta.deliveryproject.dto.CartListDto;
import com.sprta.deliveryproject.entity.Cart;
import com.sprta.deliveryproject.entity.CartList;
import com.sprta.deliveryproject.entity.Member;
import com.sprta.deliveryproject.repository.CartListRepository;
import com.sprta.deliveryproject.repository.CartRepository;
import com.sprta.deliveryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartListRepository cartListRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

    //장바구니에 메뉴 담기
    public void addCart(Member member, Long shopId, String shopName, CartDto cartDto) {
        String menuName = cartDto.getMenuname();
        Integer price = cartDto.getPrice();
        Integer amount = cartDto.getAmount();
        Integer totalPrice = addPrice(price, amount);

        Cart cart = cartRepository.findByMemberId(member.getId());

        if (cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartList cartList = new CartList(menuName, price, amount, totalPrice, shopId, shopName);
        cartList.setCart(cart);
        if (!cartList.getShopId().equals(shopId)){
            throw new IllegalArgumentException("같은 가게 메뉴만 담을 수 있습니다");
        }
        cartListRepository.save(cartList);
    }

    //장바구니에 담은 메뉴 보여주기
    public List<CartListDto> showCart(Member member) {
        Long memberId = member.getId();
        Cart cart = cartRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("장바구니에 담은 메뉴가 없습니다")
        );

        List<CartListDto> cartList = cartListRepository.findById(cart.getId())
                .stream().map(CartListDto::new)
                .collect(Collectors.toList());

        return cartList;
    }

    //장바구니 메뉴 전체삭제
    public void deleteMenuFromCart(Member member, Long menuId) {
        Cart cart = cartRepository.findByMember(member);
        CartList cartList = cartListRepository.findById(cart.getId()).orElseThrow(() -> new IllegalArgumentException("장바구니가 비어있습니다."));

        cartListRepository.delete(cartList);
    }

    public Integer addPrice(Integer price, Integer amount){
        return price * amount;
    }
}
