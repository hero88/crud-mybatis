package com.allxone.mybatisprojectservice.controller;

import com.allxone.mybatisprojectservice.dto.request.AuthenticationRequest;
import com.allxone.mybatisprojectservice.dto.request.ForgotPasswordEmail;
import com.allxone.mybatisprojectservice.dto.request.RegisterRequest;
import com.allxone.mybatisprojectservice.dto.response.ApiResponse;
import com.allxone.mybatisprojectservice.mapper.UsersMapper;
import com.allxone.mybatisprojectservice.service.AuthenticationService;
import com.allxone.mybatisprojectservice.service.EmailService;
import com.allxone.mybatisprojectservice.service.impl.JwtService;
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
            response.sendRedirect("http://localhost:3000");
        } else {
            response.sendRedirect("http://localhost:3000");
        }
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(@RequestBody ForgotPasswordEmail email) {
        String newPassword = authenticationService.forgotPassword(email.getEmail());
        if (newPassword != null) {
            emailService.sendNewPassword(email.getEmail(),newPassword);
            return ResponseEntity.ok(ApiResponse.builder()
                    .message("A new password has been sent to your email")
                    .success(true)
                    .data(null)
                    .build()
            );
        }
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Wrong email")
                .success(false)
                .data(null)
                .build()
        );
    }

    @GetMapping("/authentication/google")
    public ResponseEntity<ApiResponse> redirectToGoogleLogin() {
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Redirecting to Google Login")
                .success(true)
                .data("http://localhost:8080/oauth2/authorization/google")
                .build()
        );
    }

    @GetMapping("/authentication/facebook")
    public ResponseEntity<ApiResponse> redirectToFacebookLogin() {
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Redirecting to Facebook Login")
                .success(true)
                .data("http://localhost:8080/login/facebook")
                .build()
        );
    }
}
