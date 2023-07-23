package com.sprta.deliveryproject.controller;

import com.sprta.deliveryproject.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    //리뷰 작성 페이지
    @GetMapping("/orders/{order_id}/reviews")
    public String reviewWrite(@PathVariable(value = "order_id") Long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return "review_write";
    }
    //리뷰 수정 페이지
    @GetMapping("/orders/{order_id}/reviews/{review_id}")
    public String reviewUpdate(@PathVariable(value = "order_id")Long orderId,@PathVariable(value = "review_id")Long reviewId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return "review_update";
    }
}
