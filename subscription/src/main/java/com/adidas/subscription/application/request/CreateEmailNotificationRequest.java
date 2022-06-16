package com.adidas.subscription.application.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmailNotificationRequest {

	private Long subscriptionid;

	private String firstname;

	private String newsletterid;
}
