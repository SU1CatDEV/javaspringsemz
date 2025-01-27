package su1cat.sem9.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("ReviewMicroservice", r -> r.path("/reviews/**")
                        .uri("http://localhost:8081"))
                .route("ProductsMicroservice", r -> r.path("/products/**")
                        .uri("http://localhost:8082"))
                .route("CartMicroservice", r -> r.path("/cart/**")
                        .uri("http://localhost:8083"))
                .build();
    }
}
