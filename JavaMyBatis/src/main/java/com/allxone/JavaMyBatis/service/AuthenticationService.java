package com.allxone.JavaMyBatis.service;

import com.allxone.JavaMyBatis.dto.request.AuthenticationRequest;
import com.allxone.JavaMyBatis.dto.request.RegisterRequest;
import com.allxone.JavaMyBatis.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
