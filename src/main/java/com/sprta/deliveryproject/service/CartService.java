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

        List<Carts> CartsList = cartsRepository.findAllByMemberIdAndOrderIdIsNull(member.getId());

        if(CartsList ==null) {
            cartsRepository.save(carts);
        } else {
            for (Carts carts1 : CartsList) {
                if (carts1 == null) {
                    cartsRepository.save(carts);
                } else if (Objects.equals(carts1.getShop().getId(), shopId)) {
                    if (Objects.equals(carts1.getMenu().getMenuname(), menuName)) {
                        carts1.setAmount(carts1.getAmount() + amount);
                    } else {
                        cartsRepository.save(carts);
                    }
                } else {
                    throw new IllegalArgumentException("같은 가게 메뉴만 담을 수 있습니다");
                }
            }
        }
    }



    public Menu findMenu(long id) {
        return menuRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지않는메뉴")
        );
    }
}
