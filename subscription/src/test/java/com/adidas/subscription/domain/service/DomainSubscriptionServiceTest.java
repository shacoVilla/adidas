package com.adidas.subscription.domain.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.adidas.subscription.application.request.SubscriptionRequest;
import com.adidas.subscription.application.response.SubscriptionResponse;
import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.domain.repository.SubscriptionRepository;
import com.adidas.subscription.infrastructure.repository.h2database.DatabaseSubscriptionRepository;
import com.adidas.subscription.infrastructure.repository.h2database.H2DbSubscriptionRepository;
import com.adidas.subscription.utils.Gender;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(MockitoJUnitRunner.class)
public class DomainSubscriptionServiceTest {

	@Autowired
	private H2DbSubscriptionRepository h2databaserepository;

	private SubscriptionRepository repo;

	@InjectMocks
	private DomainSubscriptionService service;

	@BeforeEach
	public void setUp() {
		repo = new DatabaseSubscriptionRepository(h2databaserepository);
		service = new DomainSubscriptionService(repo);
	}

	@Test
	void shouldCreateNewSubscription() {
		service.createNewSubscription(getSubscriptionRequest());
		
		Assertions.assertTrue(h2databaserepository.count() == 1);
	}
	
	@Test
	void shouldCancelSubscription() {

		SubscriptionResponse response = service.createNewSubscription(getSubscriptionRequest());

		service.cancelExistingSubscription(response.getId());

		Assertions.assertTrue(h2databaserepository.count() == 0);
	}

	@Test
	void shouldGetSubscriptionDetails() {

		SubscriptionResponse responseCreated = service.createNewSubscription(getSubscriptionRequest());

		Long idSubscription = responseCreated.getId();

		SubscriptionResponse responseDetails = service.getSubscriptionDetails(idSubscription);

		Assertions.assertTrue(responseCreated.equals(responseDetails));
	}


	@Test
	void shouldReturnAListOfSubscriptions() throws Exception {

		List<Subscription> listSubs = getSubscriptionsList();

		listSubs.stream().forEach(s -> repo.save(s));

		List<SubscriptionResponse> result = service.getAllSubscriptions();

		Assertions.assertTrue(result.size() == 2);
	}

	private List<Subscription> getSubscriptionsList() {
		List<Subscription> list = new ArrayList<Subscription>();

		ZoneId defaultZoneId = ZoneId.systemDefault();

		Subscription subscription1 = Subscription.builder().firstname("dan1").consent(true)
				.datebirth(Date.from(LocalDate.of(2000, 11, 21).atStartOfDay(defaultZoneId).toInstant()))
				.email("email1").gender(Gender.FEMALE).newsletterid("sjchshdj").id(1L).build();

		Subscription subscription2 = Subscription.builder().firstname("dan2").consent(true)
				.datebirth(Date.from(LocalDate.of(2000, 11, 21).atStartOfDay(defaultZoneId).toInstant()))
				.email("email1").gender(Gender.FEMALE).newsletterid("sjchshdj").id(2L).build();
		list.add(subscription1);
		list.add(subscription2);

		return list;
	}

	private SubscriptionRequest getSubscriptionRequest() {

		ZoneId defaultZoneId = ZoneId.systemDefault();
		return SubscriptionRequest.builder().firstname("DanielV").gender(Gender.MALE)
				.datebirth(Date.from(LocalDate.of(2000, 11, 21).atStartOfDay(defaultZoneId).toInstant())).consent(true)
				.email("someth@email.com").newsletterid("1233433323").build();
	}
}