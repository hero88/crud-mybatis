package com.allxone.coinmarket.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.allxone.coinmarket.mapper.UsersMapper;
import com.allxone.coinmarket.model.Email;
import com.allxone.coinmarket.model.Users;
import com.allxone.coinmarket.model.UsersExample;
import com.allxone.coinmarket.utilities.SendMailTemplateService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ChangePassService {
	
	private final UsersMapper userMapper;
	
	private final PasswordEncoder encoder;
	
	private final MailService mailService;
	
	private final SendMailTemplateService sendMailTemplateSer;
	
	private final HttpServletRequest req;

	@Value("${MAIL_USERNAME}")
	private String MAIL_USERNAME;

	public boolean sendMailForgotPassword(String email) {
		Email mail = new Email();
		Users user = userMapper.findUserByEmail(email);
		if(user == null) {
			return false;
		}
		mail.setFrom(MAIL_USERNAME);
		mail.setTo(email);
		mail.setSubject("COIN MARKET: RECOVERY YOUR PASSWORD");
		mail.setBody(sendMailTemplateSer.getContentForConfirm(email, "templateMail", "forgotpassword", applicationUrl(req,"/recovery-password?id="+user.getId())));
		mailService.enqueue(mail);
		return true;
	}
	
	public Users changePass(Long id, String newPass) {
		UsersExample example = new UsersExample();
		example.createCriteria().andIdEqualTo(id);
		Users user = userMapper.selectByExample(example).get(0);
		user.setPassword(encoder.encode(newPass));
		userMapper.updateByExampleSelective(user, example);
		return user;
	}
	
	public void changePass(String email,String currentPass ,String newPass) {
		Users user =userMapper.findUserByUsername(email);
		user.setPassword(encoder.encode(newPass));
	}
	
	private String applicationUrl(HttpServletRequest request, String path) {
		return request.getScheme()+"://" + request.getServerName() + ":" + request.getServerPort() + path;
	}
}
