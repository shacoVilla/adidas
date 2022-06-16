package com.adidas.email.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.adidas.email.application.request.CreateEmailNotificationRequest;

@Service
public class DomainEmailService implements EmailService
{
	private static final Logger logger = LoggerFactory.getLogger(DomainEmailService.class);
	
	// private final EmailNotificationsHandler emailNotificationsHandler;
	
	// define an Autowired annotation for a constructor with the emailNotificationHandler as argument
	
	@Override
	public boolean sendEmailNotification(CreateEmailNotificationRequest emailRequest) {
		System.out.println("processing email notofication");
		// should call the email repository and process the email notification..
		
		logger.info("processing email notofication " + emailRequest.toString());
	    
		// emailNotificationsHandler.generateEmailNotification(email) -- Here the process could take a while
	    
		return true;
	}
}