package com.allxone.mybatisprojectservice.service;

import com.allxone.mybatisprojectservice.model.dto.request.RegisterRequest;
import com.allxone.mybatisprojectservice.model.dto.response.AuthenticationResponse;

public interface EmailService {
    void sendConfirmEmail(RegisterRequest user, AuthenticationResponse response);
}
