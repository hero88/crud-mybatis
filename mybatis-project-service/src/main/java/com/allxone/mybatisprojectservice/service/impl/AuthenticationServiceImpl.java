package com.allxone.mybatisprojectservice.service.impl;

import com.allxone.mybatisprojectservice.model.dto.request.AuthenticationRequest;
import com.allxone.mybatisprojectservice.model.dto.request.RegisterRequest;
import com.allxone.mybatisprojectservice.model.dto.response.AuthenticationResponse;
import com.allxone.mybatisprojectservice.mapper.UsersMapper;
import com.allxone.mybatisprojectservice.model.enums.Role;
import com.allxone.mybatisprojectservice.model.Users;
import com.allxone.mybatisprojectservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        try {
            var user = Users.builder()
                    .name(request.getName())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .gender(request.getGender())
                    .address(request.getAddress())
                    .email(request.getEmail())
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
