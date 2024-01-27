package com.allxone.coinmarket.service;

import java.util.Date;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.allxone.coinmarket.mapper.UsersMapper;
import com.allxone.coinmarket.model.Email;
import com.allxone.coinmarket.model.Users;
import com.allxone.coinmarket.utilities.SendMailTemplateService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService {

	private final UsersMapper userMapper;
	
	private final MailService mailService;
	
	private final SendMailTemplateService sendMailTemplateSer;
	
	private final HttpServletRequest req;
	
	private final PasswordEncoder encoder;
	
	public void register(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setIsActive(false);
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		userMapper.insert(user);
		Users userTmp = userMapper.findUserByEmail(user.getEmail());
		Email mail = new Email();
		mail.setFrom("musicstreaming2023@gmail.com");
		mail.setTo(user.getEmail());
		mail.setSubject("COIN MARKET: ACTIVE YOUR ACCOUNT");
		mail.setBody(sendMailTemplateSer.getContentForConfirm(user.getEmail(), "templateMail", "activated", applicationUrl(req,"/activated-account?id="+userTmp.getId())));
		mailService.enqueue(mail);
	}
	
	private String applicationUrl(HttpServletRequest request, String path) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + path;
	}
}
