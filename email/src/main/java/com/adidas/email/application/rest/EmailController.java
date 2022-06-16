package com.adidas.email.application.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.email.application.request.CreateEmailNotificationRequest;
import com.adidas.email.application.response.CreateEmailNotificationResponse;
import com.adidas.email.domain.service.EmailService;

@RestController
@RequestMapping("/email-notif")
public class EmailController {

	private final EmailService emailService;

	@Autowired
	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody()
	CreateEmailNotificationResponse createNewSubscription(
			@RequestBody final CreateEmailNotificationRequest emailRequest) {

		boolean notificated = emailService.sendEmailNotification(emailRequest);

		return new CreateEmailNotificationResponse(notificated);
	}
}
