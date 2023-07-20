package com.sprta.deliveryproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    //리뷰 작성 페이지
    @GetMapping("/orders/{order_id}/review")
    public String reviewWrite(@PathVariable(value = "order_id") Long orderId){
        return "review_write";
    }
    //리뷰 수정 페이지
    @GetMapping("/review/{review_id}")
    public String reviewUpdate(@PathVariable(value = "review_id")Long reviewId){
        return "review_update";
    }
}
