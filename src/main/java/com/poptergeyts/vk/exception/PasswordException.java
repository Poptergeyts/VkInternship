package com.poptergeyts.vk.exception;

import org.springframework.http.HttpStatus;

public class PasswordException extends AuthenticationException {
    public PasswordException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
