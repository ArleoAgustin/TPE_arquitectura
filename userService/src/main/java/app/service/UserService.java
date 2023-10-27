package app.service;

import app.repository.UserRepository;
import app.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return this.userRepository.findAll().stream().map(User::new).toList();
    }


    @Transactional
    public boolean deleteUser(Long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User save(User user) throws Exception {
        try {
            User s = userRepository.save(user);
            return new User(s);
        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    @Transactional
    public User disableAccount(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            //cambió el estado a 'd',preguntar si manejamos asi este estado
            user.setState('d');

            //guardo en la bd
            userRepository.save(user);

            return user;
        } else {
            return null; // El usuario no se encontró
        }
    }

    @Transactional
    public User enableAccount(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            //cambió el estado a 'e',preguntar si manejamos asi este estado
            user.setState('e');

            //guardo en la bd
            userRepository.save(user);

            return user;
        } else {
            return null; // El usuario no se encontró
        }
    }




}
