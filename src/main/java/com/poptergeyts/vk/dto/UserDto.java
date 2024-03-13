package com.poptergeyts.vk.dto;

import com.poptergeyts.vk.entity.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String username;
    private String password;
    private String passwordConfirm;
    private Role role;
}
