package com.allxone.JavaMyBatis.service.impl;

import com.allxone.JavaMyBatis.dto.request.AuthenticationRequest;
import com.allxone.JavaMyBatis.dto.request.RegisterRequest;
import com.allxone.JavaMyBatis.dto.response.AuthenticationResponse;
import com.allxone.JavaMyBatis.enums.AuthenticationType;
import com.allxone.JavaMyBatis.mapper.UserMapper;
import com.allxone.JavaMyBatis.model.Role;
import com.allxone.JavaMyBatis.model.User;
import com.allxone.JavaMyBatis.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        try {
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .gender(request.getGender())
                    .address(request.getAddress())
                    .age(request.getAge())
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .type(AuthenticationType.LOCAL)
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
                .build();
    }
}
