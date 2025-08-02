package com.bookcycle.service;

import com.bookcycle.dto.UserRegistrationDTO;
import com.bookcycle.model.User;

public interface AuthService {
    User registerNewUser(UserRegistrationDTO registrationDTO);
    User getCurrentUser();
} 