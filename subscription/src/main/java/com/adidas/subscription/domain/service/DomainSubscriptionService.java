package com.adidas.subscription.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.adidas.subscription.application.request.SubscriptionRequest;
import com.adidas.subscription.application.response.SubscriptionResponse;
import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.domain.repository.SubscriptionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DomainSubscriptionService implements SubscriptionService {

	private SubscriptionRepository subscriptionRepository;

	public DomainSubscriptionService(SubscriptionRepository subscriptionRepository) {
		this.subscriptionRepository = subscriptionRepository;
	}

	@Override
	public void cancelExistingSubscription(Long id) {
		final Subscription subscription = getSubscription(id);

		subscriptionRepository.remove(subscription);
		log.info("subscription " + id + "has been canceled");
	}

	@Override
	public SubscriptionResponse getSubscriptionDetails(Long id) {
		Subscription subscription = getSubscription(id);

		return mapToSubscriptionResponse(subscription);
	}

	@Override
	public List<SubscriptionResponse> getAllSubscriptions() {
		List<Subscription> subscriptions = subscriptionRepository.findAll();

		List<SubscriptionResponse> responses = subscriptions.stream().map(s -> mapToSubscriptionResponse(s))
				.collect(Collectors.toList());

		return responses;
	}

	@Override
	public SubscriptionResponse createNewSubscription(SubscriptionRequest request) {

		Subscription subscription = Subscription.builder().firstname(request.getFirstname()).gender(request.getGender())
				.datebirth(request.getDatebirth()).consent(request.getConsent()).newsletterid(request.getNewsletterid())
				.email(request.getEmail()).build();

		Subscription subscriptionSaved = subscriptionRepository.save(subscription);
		log.info("subscription {} has been saved", subscriptionSaved.getId());

		return mapToSubscriptionResponse(subscriptionSaved);

	}

	private SubscriptionResponse mapToSubscriptionResponse(Subscription subscription) {

		return SubscriptionResponse.builder().id(subscription.getId()).consent(subscription.getConsent())
				.datebirth(subscription.getDatebirth()).email(subscription.getEmail()).gender(subscription.getGender())
				.newsletterid(subscription.getNewsletterid()).id(subscription.getId()).build();
	}

	private Subscription getSubscription(Long id) {
		return subscriptionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Subscription with given id doesn't exist"));
	}
}