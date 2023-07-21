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

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartsRepository cartsRepository;
    private final MenuRepository menuRepository;


    //장바구니에 메뉴 담기
    public void addCart(Member member, CartsRequestDto cartsRequestDto) {
        Long menuId = cartsRequestDto.getMenuId();
        Integer amount = cartsRequestDto.getAmount();

        Menu menu = findMenu(menuId);
        Shop shop = menu.getShop();

        Carts carts = new Carts(amount, member, shop, menu);

        cartsRepository.save(new Carts(amount, member, shop, menu));
    }




//        Carts
//
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


    public Menu findMenu(long id) {
        return menuRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지않는메뉴")
        );
    }
}
