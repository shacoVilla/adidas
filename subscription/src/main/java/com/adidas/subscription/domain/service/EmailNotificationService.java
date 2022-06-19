package com.adidas.subscription.domain.service;

import com.adidas.subscription.application.response.SubscriptionResponse;

public interface EmailNotificationService {

	public void sendEmailNotification(SubscriptionResponse request);
}
