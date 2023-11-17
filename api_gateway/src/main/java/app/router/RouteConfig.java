package app.router;

import app.security.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, AuthenticationFilter authFilter ) {
        return builder.routes()
                .route("auth", r -> r.path("/api/authenticate" )
                        .uri("http://localhost:8088"))
                .route("register", r -> r.path("/api/register" )
                        .uri("http://localhost:8088"))
                .route("user", r -> r.path( "/api/user/**" )
                        .filters( f -> f.filter(
                                authFilter.apply( new AuthenticationFilter.Config() ) ))
                        .uri("http://localhost:8081"))
                .route("admin", r -> r.path( "/api/admin/**" )
                        .filters( f -> f.filter(
                                authFilter.apply( new AuthenticationFilter.Config() ) ))
                        .uri("http://localhost:8082"))
                .route("tariff", r -> r.path( "/api/tariff/**" )
                        .filters( f -> f.filter(
                                authFilter.apply( new AuthenticationFilter.Config() ) ))
                        .uri("http://localhost:8082"))
                .route("scooter", r -> r.path( "/api/scooter/**" )
                        .filters( f -> f.filter(
                                authFilter.apply( new AuthenticationFilter.Config() ) ))
                        .uri("http://localhost:8083"))
                .route("stopping", r -> r.path( "/api/stopping/**" )
                        .filters( f -> f.filter(
                                authFilter.apply( new AuthenticationFilter.Config() ) ))
                        .uri("http://localhost:8085"))
                .route("travel", r -> r.path( "/api/travel/**" )
                        .filters( f -> f.filter(
                                authFilter.apply( new AuthenticationFilter.Config() ) ))
                        .uri("http://localhost:8084"))
                .build();
    }
}
