package com.adidas.subscription.domain.service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.adidas.subscription.application.request.CreateEmailNotificationRequest;
import com.adidas.subscription.application.request.SubscriptionRequest;
import com.adidas.subscription.application.response.EmailResponse;
import com.adidas.subscription.application.response.SubscriptionResponse;
import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.domain.repository.SubscriptionRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class DomainSubscriptionService implements SubscriptionService {

	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private Environment env;

	@Autowired
	private WebClient webClient;

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
	public Long createNewSubscription(SubscriptionRequest request) {

		Subscription subscription = Subscription.builder().firstname(request.getFirstname()).gender(request.getGender())
				.datebirth(request.getDatebirth()).consent(request.getConsent()).newsletterid(request.getNewsletterid())
				.email(request.getEmail()).build();

		Long subscriptionId = subscriptionRepository.save(subscription);
		log.info("subscription {} has been saved", subscriptionId);

		sendEmailNotification(request, subscriptionId);

		return subscriptionId;

	}

	private void sendEmailNotification(SubscriptionRequest request, Long subscriptionId) {
		CreateEmailNotificationRequest emailNotification = CreateEmailNotificationRequest.builder()
				.firstname(request.getFirstname()).newsletterid(request.getNewsletterid())
				.subscriptionid(subscriptionId).build();

		Mono<CreateEmailNotificationRequest> mono = Mono.just(emailNotification);

		String hostname = env.getProperty("email.service.hostname");

		String uriEmailService = String.format("http://%s:8087/email-notif", hostname);

		EmailResponse response = webClient.post().uri(uriEmailService)
				.header(HttpHeaders.CONTENT_LENGTH,
						mono.map(s -> s.toString().getBytes(StandardCharsets.UTF_8).length).block().toString())
				.header(HttpHeaders.HOST, "subscriptions").body(BodyInserters.fromValue(emailNotification))
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(EmailResponse.class).block();

		log.info("email notification sent: {}", response != null ? response.getCreated() : false);
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