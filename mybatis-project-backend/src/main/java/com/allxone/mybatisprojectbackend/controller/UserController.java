package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.dto.request.ChangePasswordRequest;
import com.allxone.mybatisprojectbackend.dto.request.UserRegisterRequest;
import com.allxone.mybatisprojectbackend.dto.response.AuthenticationResponse;
import com.allxone.mybatisprojectbackend.model.User;
import com.allxone.mybatisprojectbackend.service.AuthenticationService;
import com.allxone.mybatisprojectbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserRegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
}
