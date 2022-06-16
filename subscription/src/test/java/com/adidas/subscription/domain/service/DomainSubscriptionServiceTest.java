package com.adidas.subscription.domain.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.adidas.subscription.application.request.SubscriptionRequest;
import com.adidas.subscription.application.response.SubscriptionResponse;
import com.adidas.subscription.application.rest.SubscriptionController;
import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.infrastructure.repository.h2database.H2DbSubscriptionRepository;
import com.adidas.subscription.infrastructure.repository.h2database.SubscriptionEntity;
import com.adidas.subscription.utils.Gender;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class DomainSubscriptionServiceTest
{

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	H2DbSubscriptionRepository repository;

	/*@Test
	void shouldCreateSubscription() throws Exception {
		SubscriptionRequest subscriptionRequest = getSubscriptionRequest();

		String subscriptionRequestString = mapper.writeValueAsString(subscriptionRequest);
		
		mockMvc = MockMvcBuilders.standaloneSetup(new SubscriptionController()).build();

		mockMvc.perform(MockMvcRequestBuilders.post("/subscription").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(subscriptionRequestString)).andExpect(status().isCreated());

		Assertions.assertTrue(repository.count() == 1);
	}

	@Test
	void shouldReturnAListOfSubscriptions() throws Exception {
		
		List<Subscription> listSubs = getSubscriptionsList();
		
		List<SubscriptionEntity> entities = listSubs.stream().map(s -> new SubscriptionEntity(s)).toList();
		repository.saveAll(entities);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/subscription/all")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());
		
	}

	private SubscriptionResponse toSubscriptionResponse(Subscription subscription) {
		return SubscriptionResponse.builder().id(subscription.getId()).consent(subscription.getConsent())
				.datebirth(subscription.getDatebirth()).email(subscription.getEmail()).gender(subscription.getGender())
				.newsletterid(subscription.getNewsletterid()).id(subscription.getId()).build();	
	}
	private List<Subscription> getSubscriptionsList(){
		List<Subscription> list = new ArrayList<Subscription>();
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		
		Subscription subscription1 = Subscription.builder()
				.firstname("dan1")
				.consent(true)
				.datebirth(Date.from(LocalDate.of(2000, 11, 21).atStartOfDay(defaultZoneId).toInstant()))
				.email("email1")
				.gender(Gender.FEMALE)
				.newsletterid("sjchshdj")
				.id(1L).build();
		
		Subscription subscription2 = Subscription.builder()
				.firstname("dan2")
				.consent(true)
				.datebirth(Date.from(LocalDate.of(2000, 11, 21).atStartOfDay(defaultZoneId).toInstant()))
				.email("email1")
				.gender(Gender.FEMALE)
				.newsletterid("sjchshdj")
				.id(2L).build();
		list.add(subscription1);
		list.add(subscription2);
		
		return list;
	}

	private SubscriptionRequest getSubscriptionRequest() {

		ZoneId defaultZoneId = ZoneId.systemDefault();
		return SubscriptionRequest.builder().firstname("DanielV").gender(Gender.MALE)
				.datebirth(Date.from(LocalDate.of(2000, 11, 21).atStartOfDay(defaultZoneId).toInstant())).consent(true)
				.email("someth@email.com").newsletterid("1233433323").build();
	}*/
}
