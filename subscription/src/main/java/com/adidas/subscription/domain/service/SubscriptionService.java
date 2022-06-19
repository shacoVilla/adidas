package com.adidas.subscription.domain.service;

import java.util.List;

import com.adidas.subscription.application.request.SubscriptionRequest;
import com.adidas.subscription.application.response.SubscriptionResponse;

public interface SubscriptionService {
	
	SubscriptionResponse createNewSubscription(SubscriptionRequest subscription);
	
	void cancelExistingSubscription(Long id);
	
	SubscriptionResponse getSubscriptionDetails(Long id);
	
	List<SubscriptionResponse> getAllSubscriptions(); 
}
