package com.pratap.microservices.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(predicateSpec -> predicateSpec.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
                .route(predicateSpec -> predicateSpec.path("/currency-conversion/**")
                .uri("lb://currency-conversion"))
                .route(predicateSpec -> predicateSpec.path("/currency-exchange-new/**")
                        .filters(filter ->filter.rewritePath("/currency-exchange-new/(?<segment>.*)",
                                "/currency-exchange/${segment}"))
                        .uri("lb://currency-exchange"))
                .build();
    }
}
