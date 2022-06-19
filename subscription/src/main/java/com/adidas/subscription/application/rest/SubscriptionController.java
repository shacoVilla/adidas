package com.adidas.subscription.application.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.subscription.application.request.SubscriptionRequest;
import com.adidas.subscription.application.response.CreateSubscriptionResponse;
import com.adidas.subscription.application.response.SubscriptionResponse;
import com.adidas.subscription.domain.service.EmailNotificationService;
import com.adidas.subscription.domain.service.SubscriptionService;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

	private final SubscriptionService subscriptionService;
	
	private final EmailNotificationService emailService;

	@Autowired
	public SubscriptionController(SubscriptionService subscriptionService, EmailNotificationService emailService) {
		this.subscriptionService = subscriptionService;
		this.emailService = emailService;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	CreateSubscriptionResponse createNewSubscription(@RequestBody final SubscriptionRequest subscriptionRequest) {

		SubscriptionResponse response = subscriptionService.createNewSubscription(subscriptionRequest);
		
		if (response != null)
			emailService.sendEmailNotification(response);

		return new CreateSubscriptionResponse(response.getId());
	}

	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void cancelExistingSubscription(@PathVariable final Long id) {
		subscriptionService.cancelExistingSubscription(id);
	}

	@GetMapping(value = "/all", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody()
	public List<SubscriptionResponse> getAllSubscriptions() {
		return subscriptionService.getAllSubscriptions();
	}
	
	@GetMapping(value = "/get/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody()
	public SubscriptionResponse getSubscriptionDetails(@PathVariable final Long id) {
		return subscriptionService.getSubscriptionDetails(id);
	}
}