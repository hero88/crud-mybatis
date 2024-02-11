package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.dto.request.AuthenticationRequest;
import com.allxone.mybatisprojectbackend.dto.request.UserRegisterRequest;
import com.allxone.mybatisprojectbackend.dto.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(UserRegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;
    void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    );

}
