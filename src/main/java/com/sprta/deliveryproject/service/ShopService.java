package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.ShopResponseDto;
import com.sprta.deliveryproject.entity.Menu;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.repository.MemberRepository;
import com.sprta.deliveryproject.repository.MenuRepository;
import com.sprta.deliveryproject.repository.ShopLikesRepository;
import com.sprta.deliveryproject.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final MemberRepository memberRepository;
    private final ShopLikesRepository shopLikesRepository;
    private MenuRepository menuRepository;

    public ResponseEntity<ApiResponseDto> getCategoryShops(Long categoryId) {
        List<Shop> shopList = shopRepository.findAllByCategory_IdOrderByModifiedAtDesc(categoryId);

        List<ShopResponseDto> newShopList = shopList.stream().map(ShopResponseDto::new).toList();

        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(),"카테고리 조회",newShopList));
    }

    public ResponseEntity<ApiResponseDto> getDetailShops(Long shopId) {
        ShopResponseDto shop = FindShop(shopId);

        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(), "특정 가게 조회", shop));
    }
    public ResponseEntity<ApiResponseDto> getMenus(Long id) {
        List<Menu> menus = menuRepository.findAllByShop_IdOrderByMenunameDesc(id);

        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(), "특정 가게 메뉴조회", menus));
    }
        public ShopResponseDto FindShop(Long id){
            return new ShopResponseDto(shopRepository.findById(id).orElseThrow(() ->
                    new IllegalArgumentException("해당 가게는 존재하지 않습니다. 다시 시도해주세요.")
            ));
        }

}
