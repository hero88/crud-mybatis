package com.allxone.JavaMyBatis.service.impl;

import com.allxone.JavaMyBatis.dto.request.RegisterRequest;
import com.allxone.JavaMyBatis.dto.response.AuthenticationResponse;
import com.allxone.JavaMyBatis.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendConfirmEmail(RegisterRequest user, AuthenticationResponse response) {
        SimpleMailMessage email = new SimpleMailMessage();
        String receipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String message = "Thank you for registering to us." +
                " Please click on this link to verify your account:";
        String verifyLink = "http://localhost:8080/api" + "/auth/confirmation?token=" + response.getToken();
        String sentMessage = message + "\n" + verifyLink;
        email.setTo(receipientAddress);
        email.setSubject(subject);
        email.setText(sentMessage);
        mailSender.send(email);
    }
}
