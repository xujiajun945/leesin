package com.dabanjia.leesin.gateway.configuration;

import com.dabanjia.leesin.gateway.filter.TestGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xujiajun
 * @since 2020/1/16
 */
//@Configuration
public class RouteConfiguration {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
			.route(route -> route.path("/provider/**")
				.filters(f -> f.filter(new TestGatewayFilter())
					.stripPrefix(1))
				.uri("lb://leesin-base")
				.id("leesin-base")
			)
			.build();
	}
}
