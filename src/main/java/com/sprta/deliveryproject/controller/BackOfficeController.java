//package com.sprta.deliveryproject.controller;
//
//import com.sprta.deliveryproject.dto.ApiResponseDto;
//import com.sprta.deliveryproject.dto.MenuRequestDto;
//import com.sprta.deliveryproject.security.UserDetailsImpl;
//import com.sprta.deliveryproject.security.UserDetailsServiceImpl;
//import com.sprta.deliveryproject.service.BackOfficeService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//public class BackOfficeController {
//
//    private BackOfficeService backOfficeService;
//
//    public BackOfficeController(BackOfficeService backOfficeService){
//        this.backOfficeService = backOfficeService;
//    }
//
//    @PostMapping("/shops/{id}/menus")       //특정가게 메뉴 추가(해당 가게 주인만 가능)
//    public ResponseEntity<ApiResponseDto> AddMenu(@PathVariable Long id, @RequestBody MenuRequestDto menuRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails)
//    {
//        return backOfficeService.AddMenu(id,menuRequestDto,userDetails);
//
//    }
//}
