package app.utils;

import app.model.Authority;
import app.repository.AuthorityRepository;
import app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadDates {


    private final AuthorityRepository userAuthRepository;

    public void loadAuthorities(){

        String authority1 = "ADMIN";
        String authority2 = "USER";


        Authority a1 = new Authority();
        a1.setName(authority1);
        Authority a2 = new Authority();
        a2.setName(authority2);
        this.userAuthRepository.save(a1);
        this.userAuthRepository.save(a2);
    }
}
