package com.allxone.coinmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.allxone.coinmarket.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ActivatedAccountController {
	
	private final UserService userService;

	private final HttpServletRequest req;
	
	@GetMapping("/activated-successful")
	public String success() {
		return "redirect://"+req.getServerName() + ":" + req.getServerPort()+"/java_gr2/frontend/html/page/SignInSignUp.html";
	}
	
	@GetMapping("/activated-account")
	public String activatedAccount(@RequestParam("id") Long id){
		userService.changeStatusUser(id, true, "activated");
		return "redirect://activated-successful";
	}
}
