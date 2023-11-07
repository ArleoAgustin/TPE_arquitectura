package app;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("travel", r -> r.path("/microservicio1/**")
                        .uri("http://url-microservicio-1"))
                .route("microservicio2", r -> r.path("/microservicio2/**")
                        .uri("http://url-microservicio-2"))
                // Agrega más rutas para otros microservicios
                .build();
    }
}