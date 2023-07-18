package com.sprta.deliveryproject.controller;


import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.ReviewDto;
import com.sprta.deliveryproject.security.UserDetailsImpl;
import com.sprta.deliveryproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;

    //리뷰 작성 Id =>shop Id
    @PostMapping("/shops/{id}/reviews")
    public ResponseEntity<ApiResponseDto> createReview(@PathVariable Long id, @RequestBody ReviewDto reviewRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.createReview(id, reviewRequestDto, userDetails.getMember());
        return ResponseEntity.ok().body(new ApiResponseDto("리뷰 작성 완료", HttpStatus.OK.value()));
    }

    //특정 가게 리뷰 조회 Id =>shop Id
    @GetMapping("/shops/{id}/reviews")
    public ResponseEntity<List<ReviewDto>> getShopReviews(@PathVariable Long id) {
        return ResponseEntity.ok().body(reviewService.getShopReviews(id));
    }

    //리뷰 수정
    //작성자 본인 or 관리자만 수정 삭제 가능
    @PutMapping("/reviews")
    public ResponseEntity<ApiResponseDto> updateReviews(@RequestBody ReviewDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.updateReview(requestDto, userDetails.getMember());
        return ResponseEntity.ok().body(new ApiResponseDto("리뷰 수정 완료", HttpStatus.OK.value()));
    }

    //리뷰 삭제
    //작성자 본인 or 관리자만 수정 삭제 가능
    @DeleteMapping("reviews/{reviewId}")
    public ResponseEntity<ApiResponseDto> DeleteReviews(@PathVariable Long reviewId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.deleteReview(reviewId, userDetails.getMember());
        return ResponseEntity.ok().body(new ApiResponseDto("리뷰 삭제 완료", HttpStatus.OK.value()));
    }
}
