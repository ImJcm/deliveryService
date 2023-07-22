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


    public Menu findMenu(long id) {
        return menuRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지않는메뉴")
        );
    }
}
