package com.sprta.deliveryproject.controller;


import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.ReviewRequestDto;
import com.sprta.deliveryproject.dto.ReviewResponseDto;
import com.sprta.deliveryproject.security.UserDetailsImpl;
import com.sprta.deliveryproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;


    //리뷰 작성
    @PostMapping("/reviews")
    public ResponseEntity<ApiResponseDto> createReview(@RequestBody ReviewRequestDto reviewRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.createReview(reviewRequestDto, userDetails.getMember());
        return ResponseEntity.ok().body(new ApiResponseDto("리뷰 작성 완료", HttpStatus.OK.value()));
    }

    //특정 가게 리뷰 조회
    @GetMapping("/shops/{shop_id}/reviews")
    public ResponseEntity<ApiResponseDto> getShopReviews(@PathVariable(value="shop_id") Long shopId) {
        return ResponseEntity.ok().body(new ApiResponseDto( HttpStatus.OK.value(),"리뷰 조회 완료",reviewService.getShopReviews(shopId)));
    }

    //리뷰 수정
    //작성자 본인 or 관리자만 수정 삭제 가능
    @PutMapping("/reviews")
    public ResponseEntity<ApiResponseDto> updateReviews(@RequestBody ReviewRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.updateReview(requestDto, userDetails.getMember());
        return ResponseEntity.ok().body(new ApiResponseDto("리뷰 수정 완료", HttpStatus.OK.value()));
    }

    //리뷰 삭제
    //작성자 본인 or 관리자만 수정 삭제 가능
    @DeleteMapping("reviews/{review_id}")
    public ResponseEntity<ApiResponseDto> DeleteReviews(@PathVariable(value="review_id") Long reviewId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.deleteReview(reviewId, userDetails.getMember());
        return ResponseEntity.ok().body(new ApiResponseDto("리뷰 삭제 완료", HttpStatus.OK.value()));
    }
}
