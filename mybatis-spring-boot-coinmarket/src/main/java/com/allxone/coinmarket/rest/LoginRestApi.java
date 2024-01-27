package com.allxone.coinmarket.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.allxone.coinmarket.auth.request.AuthenticationRequest;
import com.allxone.coinmarket.dto.response.ApiResponse;
import com.allxone.coinmarket.service.LoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoginRestApi {

	private final LoginService loginService;

	@PostMapping("/api/v1/login")
	public ResponseEntity<ApiResponse> login(@RequestBody AuthenticationRequest req) {
		Map<String, String> map = loginService.logIn(req);
		if (map.get("status").equalsIgnoreCase("1")) {
			return ResponseEntity.ok(ApiResponse.builder()
                    .message("Login success!!!")
                    .success(true)
                    .data(map)
                    .build());
		} else if (map.get("status").equalsIgnoreCase("0")) {
			 return ResponseEntity.ok(ApiResponse.builder()
                    .message("Not found user, please check your email!!!")
                    .success(false)
                    .data(map)
                    .build());
		} else if(map.get("status").equalsIgnoreCase("2")) {
			 return ResponseEntity.ok(ApiResponse.builder()
                    .message("Your account haven't been activated, please check your email!!!")
                    .success(false)
                    .data(map)
                    .build());
		} else {
			 return ResponseEntity.ok(ApiResponse.builder()
                    .message("Your account has some error, please check!!!")
                    .success(false)
                    .data(map)
                    .build());
		}

	}
}
