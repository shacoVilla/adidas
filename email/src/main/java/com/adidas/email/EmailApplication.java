package com.adidas.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.adidas.email.application.rest.EmailController;


@SpringBootApplication
public class EmailApplication {

	@Autowired
    public EmailController emailController;
	
	@Autowired
    public ConfigurableApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(EmailApplication.class, args);
	}
}