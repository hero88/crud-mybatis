package com.allxone.mybatisprojectbackend.service;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendVerificationEmail(String name, String toEmail, String url) throws MessagingException, UnsupportedEncodingException ;
}
