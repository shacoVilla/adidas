package com.adidas.subscription.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailResponse {

	@JsonProperty("created")
	private boolean created;
	
	public EmailResponse() {
		
	}

	public EmailResponse(boolean created) {
		this.created = created;
	}

	public boolean getCreated() {
		return created;
	}
}
