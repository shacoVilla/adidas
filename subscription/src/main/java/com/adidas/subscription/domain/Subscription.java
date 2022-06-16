package com.adidas.subscription.domain;

import java.util.Date;

import com.adidas.subscription.utils.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Subscription {

	private Long id;

	private String firstname;

	private Gender gender;

	private Date datebirth;

	private Boolean consent;

	private String newsletterid;

	private String email;
}