package com.allxone.coinmarket.serviceimpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.allxone.coinmarket.model.Email;
import com.allxone.coinmarket.service.MailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class MailServiceImpl implements MailService{
	@Autowired
	JavaMailSender mailsender;
	
	List<Email> listemail = new ArrayList<>();
	
	@Override
	public void send(Email mail) {		
		try {
			MimeMessage message = mailsender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setFrom(mail.getFrom());
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getSubject());
			helper.setText(mail.getBody(), true);
			helper.setReplyTo(mail.getFrom());
			String[] cc = mail.getCc();
			if (cc != null && cc.length > 0) {
				helper.setCc(cc);
			}
			String[] bcc = mail.getBcc();
			if (bcc != null && bcc.length > 0) {
				helper.setBcc(bcc);
			}
			mailsender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public void sendMailAttach(Email mail) {		
		try {
			MimeMessage message = mailsender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setFrom(mail.getFrom());
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getSubject());
			helper.setText(mail.getBody(), true);
			helper.setReplyTo(mail.getFrom());
			String[] cc = mail.getCc();
			if (cc != null && cc.length > 0) {
				helper.setCc(cc);
			}
			String[] bcc = mail.getBcc();
			if (bcc != null && bcc.length > 0) {
				helper.setBcc(bcc);
			}
			
			List<File> files = mail.getFiles();
			if (files.size()>0) {
				for (File file:files) {
					helper.addAttachment(file.getName(), file);
				}
			}
			// Gửi message đến SMTP server
			mailsender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	@Override
	public void send(String to, String subject, String body) {
		this.send(new Email(to,subject,body));
	}

	@Override
	public void enqueue(Email mail) {
		listemail.add(mail);
	}

	@Override
	public void queue(String to, String subject, String body) {
		enqueue(new Email(to, subject, body));
	}
	
	@Scheduled(fixedDelay = 5000)
	public void run() {
		while (!listemail.isEmpty()) {
			Email mail = listemail.remove(0);
			try {
				this.send(mail);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
