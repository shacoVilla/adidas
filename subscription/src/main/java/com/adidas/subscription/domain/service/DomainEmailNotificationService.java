package com.adidas.subscription.domain.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.adidas.subscription.application.request.CreateEmailNotificationRequest;
import com.adidas.subscription.application.response.EmailResponse;
import com.adidas.subscription.application.response.SubscriptionResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class DomainEmailNotificationService implements EmailNotificationService  {

	@Autowired
	private Environment env;

	@Autowired
	private WebClient webClient;
	
	public void sendEmailNotification(SubscriptionResponse response) {
		CreateEmailNotificationRequest emailNotification = CreateEmailNotificationRequest.builder()
				.firstname(response.getFirstname()).newsletterid(response.getNewsletterid())
				.subscriptionid(response.getId()).build();

		Mono<CreateEmailNotificationRequest> mono = Mono.just(emailNotification);

		String hostname = env.getProperty("email.service.hostname");

		String uriEmailService = String.format("http://%s:8087/email-notif", hostname);

		EmailResponse emailResponse = webClient.post().uri(uriEmailService)
				.header(HttpHeaders.CONTENT_LENGTH,
						mono.map(s -> s.toString().getBytes(StandardCharsets.UTF_8).length).block().toString())
				.header(HttpHeaders.HOST, "subscriptions").body(BodyInserters.fromValue(emailNotification))
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(EmailResponse.class).block();

		log.info("email notification sent: {}", emailResponse != null ? emailResponse.getCreated() : false);
	}
}