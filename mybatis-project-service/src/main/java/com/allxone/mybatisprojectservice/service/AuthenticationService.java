package com.allxone.mybatisprojectservice.service;

import com.allxone.mybatisprojectservice.dto.request.AuthenticationRequest;
import com.allxone.mybatisprojectservice.dto.request.RegisterRequest;
import com.allxone.mybatisprojectservice.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    String forgotPassword(String email);
    String generateRandomPassword();
}
