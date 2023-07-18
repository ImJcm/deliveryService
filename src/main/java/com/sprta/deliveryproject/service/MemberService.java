package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.ApiResponseDto;
import com.sprta.deliveryproject.dto.LoginRequestDto;
import com.sprta.deliveryproject.dto.SignupRequestDto;
import com.sprta.deliveryproject.entity.Member;
import com.sprta.deliveryproject.entity.MemberRoleEnum;
import com.sprta.deliveryproject.jwt.JwtUtil;
import com.sprta.deliveryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // "관리자권한" -> Base64 Encode
    private final String ADMIN_TOKEN = "6rSA66as7J6Q6raM7ZWc";
    public ResponseEntity<ApiResponseDto> signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        Optional<Member> checkUsername = memberRepository.findByUsername(username);
        if(checkUsername.isPresent()) {
            return ResponseEntity.status(400).body(new ApiResponseDto("아이디가 중복됩니다.", HttpStatus.BAD_REQUEST.value()));
        }

        String profilename = requestDto.getProfileName();
        Optional<Member> checkProfilename = memberRepository.findByProfilename(profilename);
        if(checkProfilename.isPresent()) {
            return ResponseEntity.status(400).body(new ApiResponseDto("중복된 프로필명입니다.",HttpStatus.BAD_REQUEST.value()));
        }

        MemberRoleEnum role = MemberRoleEnum.USER;
        if(requestDto.isAdmin()) {
            if(!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                return ResponseEntity.status(400).body(new ApiResponseDto("관리자 암호가 틀려 등록이 불가능합니다.",HttpStatus.BAD_REQUEST.value()));
            }
            role = MemberRoleEnum.ADMIN;
        }

        Member member = new Member(username,passwordEncoder.encode(password),profilename,role);

        memberRepository.save(member);

        return ResponseEntity.status(200).body(new ApiResponseDto("회원가입 성공",HttpStatus.OK.value()));
    }

    /*private final JwtUtil jwtUtil;
    public ResponseEntity<ApiResponseDto> login(LoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        Member member = memberRepository.findByUsername(username).orElse(null);
        if(member == null) {
            return ResponseEntity.status(400).body(new ApiResponseDto("해당하는 유저이름이 없습니다.",400));
        }

        String password = requestDto.getPassword();
        if(!passwordEncoder.matches(password, member.getPassword())) {
            return ResponseEntity.status(400).body(new ApiResponseDto("비밀번호가 틀립니다.",400));
        }

        MemberRoleEnum role = member.getRole();

        jwtUtil.createToken(username,role);

        return ResponseEntity.status(200).body(new ApiResponseDto("로그인 성공",HttpStatus.OK.value()));
    }*/
}
