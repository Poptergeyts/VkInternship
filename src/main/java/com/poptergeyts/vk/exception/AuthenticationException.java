package com.poptergeyts.vk.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public abstract class AuthenticationException extends RuntimeException {
    @Getter
    private final HttpStatus status;

    public AuthenticationException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
