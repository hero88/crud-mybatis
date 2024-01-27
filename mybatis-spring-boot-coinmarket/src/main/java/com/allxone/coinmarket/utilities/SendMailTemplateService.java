package com.allxone.coinmarket.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.allxone.coinmarket.auth.UserDetail.GetSubject;

@Service
public class SendMailTemplateService {
	@Autowired
	GetSubject host;

	private static final String MAIL_TEMPLATE_BASE_NAME = "templateMail";
	private static final String MAIL_TEMPLATE_PREFIX = "/templates/Mail/";
	private static final String MAIL_TEMPLATE_SUFFIX = ".html";
	private static final String UTF_8 = "UTF-8";

	private static TemplateEngine templateEngine;

	static {
		templateEngine = emailTemplateEngine();
	}

	private static TemplateEngine emailTemplateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(htmlTemplateResolver());
		templateEngine.setTemplateEngineMessageSource(emailMessageSource());
		return templateEngine;
	}

	private static ResourceBundleMessageSource emailMessageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(MAIL_TEMPLATE_BASE_NAME);
		return messageSource;
	}

	private static ITemplateResolver htmlTemplateResolver() {
		final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix(MAIL_TEMPLATE_PREFIX);
		templateResolver.setSuffix(MAIL_TEMPLATE_SUFFIX);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding(UTF_8);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	// active account
	public String getContentForConfirm(String email, String templateName, String type, String address) {
		final Context context = new Context();
		context.setVariable("email", email);
		if(type.equalsIgnoreCase("activated")) {
			context.setVariable("content","Click the button active your account");
		}else {
			context.setVariable("content","Click the button change your pass");
		}
		context.setVariable("address", address);
		return templateEngine.process(templateName, context);
	}
	
	public String getRecoveryPassword(String email, String templateName, String type, String address) {
		final Context context = new Context()	;
		context.setVariable("email", email);
		
		return templateEngine.process(templateName, context);
	}

}
