package com.adidas.email.application.response;

public class CreateEmailNotificationResponse {
	private boolean created;

	public CreateEmailNotificationResponse(boolean created) {
		this.created = created;
	}

	public boolean getCreated() {
		return created;
	}
}
