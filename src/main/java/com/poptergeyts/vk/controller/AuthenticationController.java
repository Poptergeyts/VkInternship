package com.poptergeyts.vk.controller;

import com.poptergeyts.vk.dto.JwtAuthenticationResponse;
import com.poptergeyts.vk.dto.UserDto;
import com.poptergeyts.vk.exception.PasswordException;
import com.poptergeyts.vk.exception.UserException;
import com.poptergeyts.vk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> signup(@RequestBody UserDto userForm) throws UserException {
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            throw new PasswordException("Passwords don't match");
        }
        if (!userService.saveUser(userForm)){
            throw new UserException("User already exists");
        }

        return ResponseEntity.ok("registration success");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody UserDto userForm) throws UserException {
        return ResponseEntity.ok(userService.signin(userForm));
    }
}
