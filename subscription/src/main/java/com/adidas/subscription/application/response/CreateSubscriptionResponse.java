package com.adidas.subscription.application.response;

public class CreateSubscriptionResponse {
	private Long id;

	public CreateSubscriptionResponse(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
