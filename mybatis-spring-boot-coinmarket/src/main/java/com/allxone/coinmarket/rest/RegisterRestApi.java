package com.allxone.coinmarket.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.allxone.coinmarket.dto.response.ApiResponse;
import com.allxone.coinmarket.model.Users;
import com.allxone.coinmarket.service.RegisterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class RegisterRestApi{
	private final RegisterService registerService;
	
	@PostMapping("/api/v1/register")
	public ResponseEntity<ApiResponse> register(@RequestBody Users user){
		registerService.register(user);
		return ResponseEntity.ok(ApiResponse.builder()
                .message("Register success!!!")
                .success(true)
                .data(user.getEmail())
                .build());
	}
}
