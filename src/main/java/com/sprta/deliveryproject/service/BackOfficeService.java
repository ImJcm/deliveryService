package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.MenuRequestDto;
import com.sprta.deliveryproject.entity.MemberRoleEnum;
import com.sprta.deliveryproject.entity.Menu;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.repository.MenuRepository;
import com.sprta.deliveryproject.repository.ShopRepository;
import com.sprta.deliveryproject.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BackOfficeService {

    MenuRepository menuRepository;
    ShopRepository shopRepository;
    public BackOfficeService(ShopRepository shopRepository, MenuRepository menuRepository){
        this.shopRepository = shopRepository;
        this.menuRepository = menuRepository;
    }
    public ResponseEntity<ApiResponseDto> AddMenu(Long shopId, MenuRequestDto menuRequestDto, UserDetailsImpl userDetails) {

        if(userDetails.getMember().getRole().equals(MemberRoleEnum.ADMIN)){

            Shop shop = findShop(shopId);

            Menu menu = new Menu(shop,menuRequestDto);
            if(userDetails.getUsername().equals(shop.getUsername())){
                menuRepository.save(menu);
                return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(), "메뉴추가", menu));
            }
            else{
                throw new IllegalArgumentException("권한이 없습니다."); //나중에 Exception바꿔 예외처리 해주기
            }
        }
        else{
            throw new IllegalArgumentException("권한이 없습니다."); //나중에 Exception바꿔 예외처리 해주기
        }
    }
    @Transactional
    public String updateMenu(Long shopId, Long menuId, MenuRequestDto menuRequestDto, UserDetailsImpl userDetails) {
        if(userDetails.getMember().getRole().equals(MemberRoleEnum.ADMIN)) {

            Shop shop = findShop(shopId);

            Menu menu = findMenu(menuId);
            Menu newMenu = new Menu(shop,menuRequestDto);
            //업데이트 하기
            if (userDetails.getUsername().equals(shop.getUsername())) {
                menu.update(newMenu);

                return "수정완료";
            }
            else {
                throw new IllegalArgumentException("권한이 없습니다."); //나중에 Exception바꿔 예외처리 해주기
            }
        }
        else{
            throw new IllegalArgumentException("권한이 없습니다."); //나중에 Exception바꿔 예외처리 해주기
        }
    }
    public String DeleteMenu(Long shopId, Long menuId, UserDetailsImpl userDetails) {
        if(userDetails.getMember().getRole().equals(MemberRoleEnum.ADMIN)) {

            Shop shop = findShop(shopId);

            Menu menu = findMenu(menuId);
            if (userDetails.getUsername().equals(shop.getUsername())) {
                menuRepository.delete(menu);

                return "삭제완료";
            }
            else {
                throw new IllegalArgumentException("권한이 없습니다."); //나중에 Exception바꿔 예외처리 해주기
            }
        }
        else{
            throw new IllegalArgumentException("권한이 없습니다."); //나중에 Exception바꿔 예외처리 해주기
        }
    }
    private Menu findMenu(Long menu_id){
        Menu menu = menuRepository.findById(menu_id).orElseThrow(() ->
                new IllegalArgumentException("해당 메뉴를 찾을 수 없습니다.")

        );
        return menu;
    }
    private Shop findShop(Long shop_id){
        Shop shop = shopRepository.findById(shop_id).orElseThrow(() ->
                new IllegalArgumentException("해당 가게를 찾을 수 없습니다.")

        );
        return shop;
    }


}
