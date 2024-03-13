package com.poptergeyts.vk.exception;

import org.springframework.http.HttpStatus;

public class UserException extends AuthenticationException {
    public UserException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
