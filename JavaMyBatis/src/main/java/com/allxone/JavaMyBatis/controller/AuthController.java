package com.allxone.JavaMyBatis.controller;

import com.allxone.JavaMyBatis.dto.request.AuthenticationRequest;
import com.allxone.JavaMyBatis.dto.request.RegisterRequest;
import com.allxone.JavaMyBatis.dto.response.ApiResponse;
import com.allxone.JavaMyBatis.service.AuthenticationService;
import com.allxone.JavaMyBatis.service.EmailService;
import com.allxone.JavaMyBatis.service.impl.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final EmailService emailService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        var token = authenticationService.register(request);
        if (token != null) {
            emailService.sendConfirmEmail(request,token);
            return ResponseEntity.ok(ApiResponse.builder()
                    .message("An email have sent to your email, please check!!!")
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

    @GetMapping("/register/confirmation")
    public void verifyUser(@RequestParam(name = "token")String token, HttpServletResponse response) throws Exception {
        String email = jwtService.extractUsername(token);
        if (jwtService.isTokenValid(token,email)) {
            response.sendRedirect("http://localhost:3000/confirm?status=success");
        } else {
            response.sendRedirect("http://localhost:3000/login");
        }
    }
}
