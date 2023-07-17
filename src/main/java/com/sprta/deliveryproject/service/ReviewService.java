package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.ReviewDto;
import com.sprta.deliveryproject.entity.Member;
import com.sprta.deliveryproject.entity.MemberRoleEnum;
import com.sprta.deliveryproject.entity.Review;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.repository.ReviewRepository;
import com.sprta.deliveryproject.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ShopRepository shopRepository;

    //리뷰 등록
    @Transactional(readOnly = true)
    public void createReview(Long shopId, ReviewDto requestDto, Member member) {
        //shopId에 해당하는 가게가 있는지 확인
        Shop shop = shopRepository.findById(shopId).orElseThrow(() ->
                new IllegalArgumentException("가게가 존재하지 않습니다.")
        );
        //review 등록
        Review review = Review.builder()
                .content(requestDto.getContents())
                .shop(shop)
                .member(member).build();
        reviewRepository.save(review);
        log.info(review.toString());
    }

    //특정 가게 리뷰 목록 조회
    public List<ReviewDto> getShopReviews(Long id) {
        return reviewRepository.findByShop_IdOrderByCreatedAtDesc(id).stream().map(ReviewDto::new).toList();
    }

    //리뷰 수정
    //작성자 본인 or 관리자만 수정 삭제 가능
    @Transactional
    public void updateReview(ReviewDto requestDto, Member member) {
        //shopId에 해당하는 가게가 있는지 확인
        Review review = reviewRepository.findById(requestDto.getId()).orElseThrow(() ->
                new IllegalArgumentException("리뷰가 존재하지 않습니다.")
        );

        if (!(member.getId().equals(review.getMember().getId()) || member.getRole().equals(MemberRoleEnum.ADMIN))) {
            throw new IllegalArgumentException("리뷰를 수정할 권한이 없습니다.");
        }
        review.updateContent(requestDto.getContents());
    }

    //리뷰 삭제
    //작성자 본인 or 관리자만 수정 삭제 가능
    @Transactional
    public void deleteReview(Long reviewId, Member member) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->
                new IllegalArgumentException("리뷰가 존재하지 않습니다.")
        );

        if (!(member.getId().equals(review.getMember().getId()) || member.getRole().equals(MemberRoleEnum.ADMIN))) {
            throw new IllegalArgumentException("리뷰를 삭제할 권한이 없습니다.");
        }
        reviewRepository.delete(review);
    }
}
