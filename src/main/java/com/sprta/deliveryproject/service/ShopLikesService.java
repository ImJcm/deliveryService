package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.entity.Member;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.entity.ShopLike;
import com.sprta.deliveryproject.repository.ShopLikesRepository;
import com.sprta.deliveryproject.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShopLikesService {
    private final ShopLikesRepository shopLikesRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public void createShopLikes(Long shopId, Member member) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new IllegalArgumentException("적절하지 않은 가게 ID 입니다."));
        //좋아요를 이미 눌렀는지 확인
        if (shopLikesRepository.existsByShop_IdAndMember_Id(shopId, member.getId())) {
            throw new IllegalArgumentException("이미 해당 가게를 찜하셨습니다.");
        }
        ShopLike shopLike = ShopLike.builder().shop(shop).member(member).build();
        shopLikesRepository.save(shopLike);
    }


    @Transactional
    public void deleteShopLikes(Long id, Member member) {
        // id 에 해당하는 좋아요가 존재하는지 확인
        ShopLike shopLike = shopLikesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 좋아요 ID 입니다."));
        //본인이 누른 좋아요일 경우
        if (!shopLike.getMember().getId().equals(member.getId())) {
            throw new IllegalArgumentException("본인이 누른 좋아요가 아닙니다.");
        }
        shopLikesRepository.delete(shopLike);
    }
}
