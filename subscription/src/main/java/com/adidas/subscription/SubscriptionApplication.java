package com.adidas.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.adidas.subscription.application.rest.SubscriptionController;

@EnableWebMvc
@SpringBootApplication
@EnableEurekaClient
public class SubscriptionApplication {

	@Autowired
    public SubscriptionController subscriptionController;
	
	@Autowired
    public ConfigurableApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(SubscriptionApplication.class, args);
	}
}