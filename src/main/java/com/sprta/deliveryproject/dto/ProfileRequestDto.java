package com.sprta.deliveryproject.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequestDto {
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "사용자아이디 형식이 올바르지 않습니다.")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9`~!@#$%^&*()-_=+]{8,10}$", message = "패스워드 형식이 올바르지 않습니다.")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9`~!@#$%^&*()-_=+]{8,10}$", message = "패스워드체크 형식이 올바르지 않습니다.")
    private String checkpassword;

    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,15}$", message = "프로필명 형식이 올바르지 않습니다.")
    private String profilename;

    @Pattern(regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?", message = "이메일 형식이 아닙니다.")
    private String email;
}
