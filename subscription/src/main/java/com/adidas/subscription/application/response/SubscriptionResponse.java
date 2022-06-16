package com.adidas.subscription.application.response;

import java.util.Date;

import com.adidas.subscription.utils.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResponse {

	private Long id;

	private String firstname;

	private Gender gender;

	private Date datebirth;

	private Boolean consent;

	private String newsletterid;

	private String email;
}
