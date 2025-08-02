package com.bookcycle.dto;

import com.bookcycle.model.Role;
import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String name;
    private String address;
    private String contact;
    private String email;
    private String password;
    private Role role;
} 