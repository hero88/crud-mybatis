package com.allxone.coinmarket.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.allxone.coinmarket.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ActivatedAccountController {
	
	private final UserService userService;
	
	@GetMapping("/activated-successful")
	public String success(HttpServletRequest req) {
		return "redirect:/"+ req.getServerName() + "/java_gr2/frontend/html/page/SignInSignUp.html";
	}
	
	@GetMapping("/activated-account")
	public String activatedAccount(@RequestParam("id") Long id,HttpServletRequest req){
		userService.changeStatusUser(id, true, "activated");
		return "redirect:/"+ req.getServerName() + "/activated-successful";
	}
}
