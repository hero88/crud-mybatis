package com.allxone.mybatisprojectservice.controller;

import com.allxone.mybatisprojectservice.dto.request.AuthenticationRequest;
import com.allxone.mybatisprojectservice.dto.request.RegisterRequest;
import com.allxone.mybatisprojectservice.dto.response.ApiResponse;
import com.allxone.mybatisprojectservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        var token = authenticationService.register(request);
        if (token != null) {
            return ResponseEntity.ok(ApiResponse.builder()
                    .message("Sign in successfully!!!")
                    .success(true)
                    .data(token)
                    .build()
            );
        } else {
            return ResponseEntity.ok(ApiResponse.builder()
                    .message("Invalid password or email is already existed")
                    .success(false)
                    .data(null)
                    .build()
            );
        }

    }

    @PostMapping("/authentication")
    public ResponseEntity<ApiResponse> authentication(@RequestBody AuthenticationRequest authenticationRequest) {
        var token = authenticationService.authenticate(authenticationRequest);
        if (token != null) {
            return ResponseEntity.ok(ApiResponse.builder()
                    .message("Login successfully!!!")
                    .success(true)
                    .data(token)
                    .build()
            );
        } else {
            return ResponseEntity.ok(ApiResponse.builder()
                    .message("Wrong email or password")
                    .success(false)
                    .data(null)
                    .build()
            );
        }
    }
}
