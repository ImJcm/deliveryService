package com.sprta.deliveryproject.controller;

import com.sprta.deliveryproject.dto.*;
import com.sprta.deliveryproject.entity.Shop;
import com.sprta.deliveryproject.security.UserDetailsImpl;
import com.sprta.deliveryproject.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<ApiResponseDto> signupMember(@Valid @RequestBody SignupRequestDto requestDto) {
        return memberService.signupMember(requestDto);
    }

    @GetMapping("/{member_id}")
    @ResponseBody
    public MemberResponseDto getMember(@PathVariable Long member_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memberService.getMember(member_id, userDetails.getMember());
    }

    @PutMapping("/{member_id}")
    public ResponseEntity<ApiResponseDto> modifyMember(@PathVariable Long member_id, @RequestBody ProfileRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memberService.modifyMember(member_id, requestDto, userDetails.getMember());
    }

    @DeleteMapping("/{member_id}")
    public ResponseEntity<ApiResponseDto> deleteMember(@PathVariable Long member_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memberService.deleteMember(member_id, userDetails.getMember());
    }
}
