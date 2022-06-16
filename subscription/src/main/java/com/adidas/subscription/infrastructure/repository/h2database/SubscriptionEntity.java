package com.adidas.subscription.infrastructure.repository.h2database;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.utils.Gender;

@Entity
@Table(name = "Subscription")
public class SubscriptionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "firstname", nullable = true)
	private String firstname;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "gender")
	private Gender gender;

	@Column(name = "datebirth", nullable = false)
	private Date datebirth;

	@Column(name = "consent", nullable = false)
	private boolean consent;

	@Column(name = "newsletterid", nullable = false)
	private String newsletterid;

	public SubscriptionEntity(String firstname, Gender gender, Date datebirth, boolean consent,
			String newsletterid, String email) {
		this.firstname = firstname;
		this.gender = gender;
		this.datebirth = datebirth;
		this.consent = consent;
		this.newsletterid = newsletterid;
		this.email = email;
	}

	public SubscriptionEntity() {
	}

	public SubscriptionEntity(Subscription subscription) {
		this.id = subscription.getId();
		this.firstname = subscription.getFirstname();
		this.gender = subscription.getGender();
		this.datebirth = subscription.getDatebirth();
		this.consent = subscription.getConsent();
		this.newsletterid = subscription.getNewsletterid();
		this.email = subscription.getEmail();
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public Optional<String> getFirstname() {
		return Optional.ofNullable(firstname);
	}

	public Optional<Gender> getGender() {
		return Optional.ofNullable(gender);
	}

	public Date getDatebirth() {
		return datebirth;
	}

	public boolean isConsent() {
		return consent;
	}

	public String getNewsletterid() {
		return this.newsletterid;
	}

	public Subscription toSubscription() {
		return new Subscription(id, firstname, gender, datebirth, consent, newsletterid, email);
	}
}