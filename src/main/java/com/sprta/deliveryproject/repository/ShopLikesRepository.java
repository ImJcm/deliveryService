package com.sprta.deliveryproject.repository;

import com.sprta.deliveryproject.entity.ShopLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopLikesRepository extends JpaRepository<ShopLike,Long> {
    //가게 ID와 Member Id를 확인해 member가 해당 가게에 대해 좋아요를 이미 눌렀는지 확인할 때 사용
    Boolean existsByShop_IdAndMember_Id(Long shopId,Long memberId);

    List<ShopLike> findAllByMember_Id(Long member_id);
}
