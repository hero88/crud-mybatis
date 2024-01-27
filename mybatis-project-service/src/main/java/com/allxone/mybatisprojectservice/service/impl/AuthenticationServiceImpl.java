package com.allxone.mybatisprojectservice.service.impl;

import com.allxone.mybatisprojectservice.dto.request.AuthenticationRequest;
import com.allxone.mybatisprojectservice.dto.request.RegisterRequest;
import com.allxone.mybatisprojectservice.dto.response.AuthenticationResponse;
import com.allxone.mybatisprojectservice.mapper.UsersMapper;
import com.allxone.mybatisprojectservice.model.Role;
import com.allxone.mybatisprojectservice.model.Users;
import com.allxone.mybatisprojectservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final int RANDOM_STRING_LENGTH = 8;
    private static final String CHARACTER_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        try {
            var user = Users.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .gender(request.getGender())
                    .address(request.getAddress())
                    .age(request.getAge())
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .role(Role.USER)
                    .build();
            usersMapper.insertUser(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        var user = usersMapper.findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .lastName(user.getLastName())
                .role(user.getRole().toString())
                .build();
    }

    @Override
    public String forgotPassword(String email) {
        try {
            var user = usersMapper.findByEmail(email);
            if (user != null) {
                String password = generateRandomPassword();
                String newPassword = passwordEncoder.encode(password);
                usersMapper.updatePasswordByEmail(email, newPassword);
                return password;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String generateRandomPassword() {
        Random random = new Random();
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            int index = random.nextInt(CHARACTER_SET.length());
            randomString.append(CHARACTER_SET.charAt(index));
        }
        return randomString.toString();
    }
}
