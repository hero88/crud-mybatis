package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.convert.UserConvert;
import com.allxone.mybatisprojectbackend.dto.request.AuthenticationRequest;
import com.allxone.mybatisprojectbackend.dto.request.UserRegisterRequest;
import com.allxone.mybatisprojectbackend.dto.response.AuthenticationResponse;
import com.allxone.mybatisprojectbackend.model.Token;
import com.allxone.mybatisprojectbackend.model.User;
import com.allxone.mybatisprojectbackend.service.AuthenticationService;
import com.allxone.mybatisprojectbackend.service.Impl.TokenService;
import com.allxone.mybatisprojectbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final TokenService tokenService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserRegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token){
        Token theToken = tokenService.findByToken(token).orElseThrow();
        User user = userService.getUserById(theToken.getUserId());
        HashMap<String, Object> result = new HashMap<String, Object>();

        if (theToken.isExpired()){
            userService.deleteUserById(user.getId());
            result.put("success", false);
            result.put("message", "The token has expired, please register again and check your email");
            result.put("data", null);
        }

        if (user.isEnabled()){
            result.put("success", false);
            result.put("message", "This account has already been verified");
            result.put("data", null);
        }

        userService.activatedUser(user);
        result.put("success", true);
        result.put("message", "Email verified successfully. Now you can login to your account");
        result.put("data", null);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request
    ) {

        HashMap<String, Object> result = new HashMap<String, Object>();

        try{
            AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
            result.put("success", true);
            result.put("message", "Call API successfully");
            result.put("data",authenticationResponse );
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Account does not verify");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }
}
