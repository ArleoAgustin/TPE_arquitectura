package app.config;

import app.security.AuthorityConstants;
import app.security.jwt.JwtFilter;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class HttpConfig {
    private final JwtParser jwtParser;
    private final String secret = "QJeKx+s7XIv1WbBlj7vJ9CD3Ozj1rB3qjlNZY9ofWKJSaBNBo5r1q9Rru/OWlYb+UHV1n4/LJl1OBYYZZ7rhJEnn5peyHCd+eLJfRdArE37pc+QDIsJlabQtR7tYRa+SnvGRyL01uZsK33+gezV+/GPXBnPTj8fOojDUzJiPAvE=";

    public HttpConfig() {
        final var keyBytes = Decoders.BASE64.decode(secret);
        final var key = Keys.hmacShaKeyFor( keyBytes );
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http ) throws Exception {
        http.addFilterBefore( new JwtFilter( jwtParser ), UsernamePasswordAuthenticationFilter.class);
        http.csrf( AbstractHttpConfigurer::disable )
                .authorizeRequests()
                .requestMatchers( "api/stopping" ).hasAuthority( AuthorityConstants.USER )
                .requestMatchers( "api/stopping" ).hasAuthority( AuthorityConstants.USER )
                .anyRequest().authenticated();
        http.anonymous( AbstractHttpConfigurer::disable )
                .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http.httpBasic( Customizer.withDefaults() );
        return http.build();
    }
}
