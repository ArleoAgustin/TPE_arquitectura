package app;

import app.utils.LoadDates;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {

    @Autowired
    private LoadDates loadDates;

    public static void main(String[] args) {

        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @PostConstruct
    public void init(){
        loadDates.loadAuthorities();
    }
}
