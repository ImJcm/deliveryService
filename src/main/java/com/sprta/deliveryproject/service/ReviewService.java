package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.ReviewRequestDto;
import com.sprta.deliveryproject.dto.ReviewResponseDto;
import com.sprta.deliveryproject.entity.Member;
import com.sprta.deliveryproject.entity.MemberRoleEnum;
import com.sprta.deliveryproject.entity.Order;
import com.sprta.deliveryproject.entity.Review;
import com.sprta.deliveryproject.repository.OrderRepository;
import com.sprta.deliveryproject.repository.ReviewRepository;
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
    private final OrderRepository orderRepository;

    //리뷰 등록
    @Transactional
    public void createReview(ReviewRequestDto requestDto, Member member) {
        //orderId에 해당하는 Id가 있는지 확인
        Order order = orderRepository.findById(requestDto.getOrderId()).orElseThrow(() ->
                new IllegalArgumentException("주문이 존재하지 않습니다.")
        );
        //orderId에 해당하는 review 가 있는지 확인
        if (reviewRepository.existsByOrder_Id(requestDto.getOrderId())) {
            throw new IllegalArgumentException("해당 주문에 대한 리뷰를 이미 작성하셨습니다.");
        }
        //review 등록
        Review review = Review.builder()
                .content(requestDto.getContents())
                .order(order)
                .member(member).build();
        reviewRepository.save(review);
        log.info(review.toString());
    }

    //특정 가게 리뷰 목록 조회
    public List<ReviewResponseDto> getShopReviews(Long id) {
        //return reviewRepository.findByShop_IdOrderByCreatedAtDesc(id).stream().map(ReviewDto::new).toList();
        return reviewRepository.findByOrder_Shop_IdOrderByCreatedAtDesc(id).stream().map(ReviewResponseDto::new).toList();
    }

    //리뷰 수정
    //작성자 본인 or 관리자만 수정 삭제 가능
    @Transactional
    public void updateReview(ReviewRequestDto requestDto, Member member) {
        //review Id에 해당하는 리뷰가 존재하는지 확인
        Review review = reviewRepository.findById(requestDto.getReviewId()).orElseThrow(() ->
                new IllegalArgumentException("리뷰가 존재하지 않습니다."));

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
