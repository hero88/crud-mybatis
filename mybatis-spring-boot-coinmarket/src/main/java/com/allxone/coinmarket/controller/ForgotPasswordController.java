package com.allxone.coinmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ForgotPasswordController {
	
	@GetMapping("/recovery-password")
	public String recoveryPage() {
		return "User/forgotpasschange";
	}
}
