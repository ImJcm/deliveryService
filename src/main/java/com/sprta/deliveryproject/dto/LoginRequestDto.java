package com.sprta.deliveryproject.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String username;
    private String password;

}
