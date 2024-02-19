package com.allxone.coinmarket.model;

import java.io.File;
import java.util.List;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {

	@Value("${MAIL_USERNAME}")
	private String MAIL_USERNAME;

	private String from;
	private String to;
	private String subject;
	private String body;
	private String[] bcc;
	private String[] cc;
	private List<File> files;

	public Email(String to, String subject, String body) {
		this.from = MAIL_USERNAME;
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
