package com.allxone.coinmarket.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allxone.coinmarket.dto.response.ApiResponse;
import com.allxone.coinmarket.model.Users;
import com.allxone.coinmarket.service.ChangePassService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PasswordRestApi {
	private final ChangePassService changePassService;
	
	@GetMapping("/api/v1/sendmail-forgot-password")
	public ResponseEntity<ApiResponse> sendMailForgot(@RequestParam("email") String email){
		Boolean check = changePassService.sendMailForgotPassword(email);
		return ResponseEntity.ok(ApiResponse.builder()
                .message(check==false ? "User is not found, please check your email":"Check your email!!!")
                .success(check)
                .data(check)
                .build());
	}
	
	@GetMapping("/api/v1/recovery-password")
	public ResponseEntity<ApiResponse> recoverPass(@RequestParam("id") Long id, @RequestParam("newPass") String newPass){
		Users user = changePassService.changePass(id, newPass);
		return ResponseEntity.ok(ApiResponse.builder()
                .message("Your password was changed!!!")
                .success(true)
                .data(user)
                .build());
	}
}
