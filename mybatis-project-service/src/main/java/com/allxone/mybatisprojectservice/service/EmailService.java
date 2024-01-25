package com.allxone.mybatisprojectservice.service;

import com.allxone.mybatisprojectservice.dto.request.RegisterRequest;
import com.allxone.mybatisprojectservice.dto.response.AuthenticationResponse;

public interface EmailService {
    void sendConfirmEmail(RegisterRequest user, AuthenticationResponse response);
}
