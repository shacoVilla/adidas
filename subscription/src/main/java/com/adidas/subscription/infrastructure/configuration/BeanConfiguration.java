package com.adidas.subscription.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.adidas.subscription.SubscriptionApplication;
import com.adidas.subscription.domain.repository.SubscriptionRepository;
import com.adidas.subscription.domain.service.DomainSubscriptionService;
import com.adidas.subscription.domain.service.SubscriptionService;

@Configuration
@ComponentScan(basePackageClasses = SubscriptionApplication.class)
public class BeanConfiguration {

	@Bean
    SubscriptionService subscriptionService(SubscriptionRepository subscriptionRepository) {
        return new DomainSubscriptionService(subscriptionRepository);
    }
}
