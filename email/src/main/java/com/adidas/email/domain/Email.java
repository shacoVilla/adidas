package com.adidas.email.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Email {

	private Long subscriptionid;
	
	private String firstname;
	
	private String newsletterid;
}