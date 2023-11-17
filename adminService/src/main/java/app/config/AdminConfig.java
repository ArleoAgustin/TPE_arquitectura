package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AdminConfig {

    @Bean("clienteRestAdmin")
    @Primary
    public RestTemplate registrarTemplate() {
        return new RestTemplate();
    }

}
