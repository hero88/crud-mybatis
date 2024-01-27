package com.allxone.coinmarket.service;

import com.allxone.coinmarket.model.Email;

public interface MailService {
	void send(Email mail);
	void send(String to, String subject, String body);
	void enqueue(Email mail);
	void queue(String to, String subject, String body);
}
