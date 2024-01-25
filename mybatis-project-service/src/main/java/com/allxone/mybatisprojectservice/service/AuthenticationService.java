package com.allxone.mybatisprojectservice.service;

import com.allxone.mybatisprojectservice.model.dto.request.AuthenticationRequest;
import com.allxone.mybatisprojectservice.model.dto.request.RegisterRequest;
import com.allxone.mybatisprojectservice.model.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
