package com.sprta.deliveryproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    //리뷰 작성 페이지
    @GetMapping("/review-write")
    public String reviewWrite(){
        return "review_write";
    }
    //리뷰 수정 페이지
    @GetMapping("/review-update")
    public String reviewUpdate(){
        return "review_update";
    }
}
