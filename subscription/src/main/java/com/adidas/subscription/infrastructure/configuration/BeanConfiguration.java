package com.adidas.subscription.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.adidas.subscription.SubscriptionApplication;
import com.adidas.subscription.domain.repository.SubscriptionRepository;
import com.adidas.subscription.domain.service.DomainEmailNotificationService;
import com.adidas.subscription.domain.service.DomainSubscriptionService;
import com.adidas.subscription.domain.service.EmailNotificationService;
import com.adidas.subscription.domain.service.SubscriptionService;

@Configuration
@ComponentScan(basePackageClasses = SubscriptionApplication.class)
public class BeanConfiguration {

	@Bean
	EmailNotificationService emailNotificationService() {
		return new DomainEmailNotificationService();
	}

	@Bean
    SubscriptionService subscriptionService(SubscriptionRepository subscriptionRepository) {
        return new DomainSubscriptionService(subscriptionRepository);
    }
}
