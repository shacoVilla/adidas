package com.adidas.cloud.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adidas.cloud.gateway.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes().route("authentication", r -> r.path("/authentication/**").filters(f -> f.filter(filter)).uri("lb://authentication"))
				.route("subscription", r -> r.path("/subscription/**").filters(f -> f.filter(filter)).uri("lb://subscription"))
				.build();
	}
}
