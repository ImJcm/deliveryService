package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.CartsRequestDto;
import com.sprta.deliveryproject.entity.Carts;
import com.sprta.deliveryproject.entity.Member;
import com.sprta.deliveryproject.entity.Menu;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.repository.CartsRepository;
import com.sprta.deliveryproject.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartsRepository cartsRepository;
    private final MenuRepository menuRepository;


    //장바구니에 메뉴 담기
    @Transactional
    public void addCart(Member member, CartsRequestDto cartsRequestDto) {
        Long menuId = cartsRequestDto.getMenuId();
        Integer amount = cartsRequestDto.getAmount();

        Menu menu = findMenu(menuId);
        Shop shop = menu.getShop();

        Carts carts = new Carts(amount, member, shop, menu);

        String menuName = menu.getMenuname();
        Long shopId = shop.getId();

        cartsRepository.save(carts);

//        List<Carts> CartsList = cartsRepository.findAllByMemberIdAndOrderIdIsNull(member.getId());
//        Carts nullCheckCarts = cartsRepository.findTopByMemberId(member.getId()).orElse(null);

//        if (CartsList.isEmpty()) { //담은 메뉴가 없으면 바로 장바구니에 담는다
//            cartsRepository.save(carts);
//        } else { //담은 메뉴가 있다면
//            if (Objects.equals(checkShopId, shopId)) { //담겨진 메뉴의 shopID 와 비교
//                for (Carts carts1 : CartsList) {
//                    if (carts1.getMenu().getMenuname().equals(menuName)) {
//                        carts1.setAmount(carts.getAmount() + amount);
//                    }
//                }
//                cartsRepository.save(carts);
//            } else {
//                throw new IllegalArgumentException("같은 가게 메뉴만 담을 수 있습니다");
//            }
//        }

        //        Carts nullCheckCarts = cartsRepository.findTopByCartId(cart.getId()).orElse(null);
//        List<Carts> menuCheckCarts = cartListRepository.findByCartId(cart.getId());
//
//        if(nullCheckCarts == null) {//장바구니가 비어있는 상태인지
//            cartListRepository.save(carts);
//        } else {
//            if (nullCheckCarts.getShopId().equals(carts.getShopId())){//같은 가게 메뉴를 담았는지
//                if(cartListRepository.findByMenuId(menuId).isPresent()){
//                    Carts upCarts = cartListRepository.findByMenuName(menu.getMenuname());
//                    Integer upAmount = upCarts.getAmount() + carts.getAmount();
//                    Integer upTotalPrice = upCarts.getTotalPrice() + carts.getTotalPrice();
//                    upCarts.setAmount(upAmount);
//                    upCarts.setTotalPrice(upTotalPrice);
//                } else {
//                    cartListRepository.save(carts);
//                }
//            } else {
//                throw new IllegalArgumentException("같은 가게 메뉴만 담을 수 있습니다");
//            }
//        }
    }


    public Menu findMenu(long id) {
        return menuRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지않는메뉴")
        );
    }
}
