package org.onlinebookstore.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/microservice/**")
						.filters(f -> f.rewritePath("/microservice",""))
						.uri("lb://micro-service")
				).route(r -> r.path("/function/**")
								.filters(f -> f.rewritePath("/function",""))
								.uri("lb://function")
				).route(r -> r.path("/graphql/**")
						.filters(f -> f.rewritePath("/graphql","/graphql"))
						.uri("lb://online-bookstore-backend")
				).route(r->r.path("/**")
						.filters(f -> f)
						.uri("lb://online-bookstore-backend")
				)
				.build();
	}
}
