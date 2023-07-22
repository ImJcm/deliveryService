package com.sprta.deliveryproject.exception;

import lombok.Getter;

import java.util.Map;
@Getter
public class ProfileValidationException extends RuntimeException{
    private Map<String, String> errorMap;

    public ProfileValidationException(String message, Map<String,String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }
}

