package com.sprta.deliveryproject.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SignupRequestDto {
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "사용자아이디 형식이 올바르지 않습니다.")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9`~!@#$%^&*()-_=+]{8,10}$", message = "패스워드 형식이 올바르지 않습니다.")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9`~!@#$%^&*()-_=+]{8,10}$", message = "패스워드체크 형식이 옳바르지 않습니다.")
    private String checkpassword;

    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,15}$", message = "프로필명 형식이 올바르지 않습니다.")
    private String profileName;

    //@Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$", message = "이메일 형식이 아닙니다.")
    @Pattern(regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?", message = "이메일 형식이 아닙니다.")
    private String email;

    private boolean admin = false;
    private String adminToken = ""; //실제 서비스에서 admin 등록하려면 사업자등록 유효성 검사를 해야할 필요가 있음
}
