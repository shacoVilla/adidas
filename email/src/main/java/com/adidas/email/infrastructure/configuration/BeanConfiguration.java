package com.adidas.email.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.adidas.email.EmailApplication;
import com.adidas.email.domain.service.DomainEmailService;
import com.adidas.email.domain.service.EmailService;

@Configuration
@ComponentScan(basePackageClasses = EmailApplication.class)
public class BeanConfiguration {

	@Bean
    EmailService emailService() {
        return new DomainEmailService();
    }
}