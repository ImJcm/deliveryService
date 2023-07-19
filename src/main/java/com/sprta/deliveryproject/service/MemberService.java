package com.sprta.deliveryproject.service;

import com.sprta.deliveryproject.dto.*;
import com.sprta.deliveryproject.entity.Member;
import com.sprta.deliveryproject.entity.MemberRoleEnum;
import com.sprta.deliveryproject.jwt.JwtUtil;
import com.sprta.deliveryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // "관리자권한" -> Base64 Encode
    private final String ADMIN_TOKEN = "6rSA66as7J6Q6raM7ZWc";
    public ResponseEntity<ApiResponseDto> signupMember(SignupRequestDto requestDto) {
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

        //Email 검증 - 필요하다면
        String email = requestDto.getEmail();

        Member member = new Member(username,passwordEncoder.encode(password),profilename,email,role);

        memberRepository.save(member);

        return ResponseEntity.status(200).body(new ApiResponseDto("회원가입 성공",HttpStatus.OK.value()));
    }

    public MemberResponseDto getMember(Long member_id, Member member) {
        Member origin_member = memberRepository.findById(member_id).orElseThrow(() -> new IllegalArgumentException("회원정보가 존재하지 않습니다."));

        if(origin_member.getId() != member.getId()) {
            throw new IllegalArgumentException("회원정보가 일치하지 않습니다.");
        }

        return new MemberResponseDto(origin_member);
    }

    @Transactional
    public ResponseEntity<ApiResponseDto> modifyMember(Long user_id, ProfileRequestDto requestDto, Member member) {
        Optional<Member> checkMember = memberRepository.findById(user_id);

        if(!checkMember.isPresent()) {
            return ResponseEntity.status(400).body(new ApiResponseDto("유저가 존재하지 않습니다.",HttpStatus.BAD_REQUEST.value()));
        }

        if(checkMember.get().getId() != member.getId()) {
            return ResponseEntity.status(400).body(new ApiResponseDto("현재 접속 유저와 다릅니다.",HttpStatus.BAD_REQUEST.value()));
        }

        String password = requestDto.getPassword();
        String checkpassword = requestDto.getCheckpassword();
        if(!password.equals(checkpassword)) {
            return ResponseEntity.status(400).body(new ApiResponseDto("비밀번호가 일치하지 않습니다.",HttpStatus.BAD_REQUEST.value()));
        }

        //프로필 수정 시 넘어온 password Encode
        requestDto.setPassword(passwordEncoder.encode(password));

        checkMember.get().modify(requestDto);

        return ResponseEntity.status(200).body(new ApiResponseDto("회원 프로필 수정 성공",HttpStatus.OK.value()));
    }

    @Transactional
    public ResponseEntity<ApiResponseDto> deleteMember(Long member_id, Member member) {
        Optional<Member> checkMember = memberRepository.findById(member_id);

        if(!checkMember.isPresent() || checkMember.get().getId() != member.getId()) {
            return ResponseEntity.status(400).body(new ApiResponseDto("회원이 존재하지 않거나, 회원정보가 일치하지 않습니다.",HttpStatus.BAD_REQUEST.value()));
        }

        memberRepository.delete(checkMember.get());

        return ResponseEntity.status(200).body(new ApiResponseDto("회원 삭제 성공",HttpStatus.OK.value()));
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
