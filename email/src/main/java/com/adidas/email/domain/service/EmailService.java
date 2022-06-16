package com.adidas.email.domain.service;

import com.adidas.email.application.request.CreateEmailNotificationRequest;

public interface EmailService {

	public boolean sendEmailNotification(CreateEmailNotificationRequest emailRequest);
}
