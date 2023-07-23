package com.sprta.deliveryproject.repository;

import com.sprta.deliveryproject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    //특정 가게 리뷰 조회 최신순(생성일 기준 내림차순)
    List<Review> findByOrder_Shop_IdOrderByCreatedAtDesc(Long shopId);
    Boolean existsByOrder_Id(Long orderId);

    Review findByOrderIdAndMemberId(Long order_id, Long member_id);
}
