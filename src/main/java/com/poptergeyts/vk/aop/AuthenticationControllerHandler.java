package com.poptergeyts.vk.aop;

import com.poptergeyts.vk.exception.PasswordException;
import com.poptergeyts.vk.exception.AuthenticationException;
import com.poptergeyts.vk.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthenticationControllerHandler {
    @ExceptionHandler({
            UserException.class,
            PasswordException.class
    })
    public ResponseEntity<String> handleRegistrationControllerExceptions(AuthenticationException authenticationException) {
        return new ResponseEntity<>(authenticationException.getMessage(), authenticationException.getStatus());
    }
}
