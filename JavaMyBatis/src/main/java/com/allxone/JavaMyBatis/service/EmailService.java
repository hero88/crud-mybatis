package com.allxone.JavaMyBatis.service;

import com.allxone.JavaMyBatis.dto.request.RegisterRequest;
import com.allxone.JavaMyBatis.dto.response.AuthenticationResponse;


public interface EmailService {
    void sendConfirmEmail(RegisterRequest user, AuthenticationResponse response);
}
