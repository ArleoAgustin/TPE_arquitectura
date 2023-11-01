package app.service;

import app.repository.UserRepository;
import app.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public static final Character AVALIABLE = 'A';
    public static final Character DISABLED = 'D';

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return this.userRepository.findAll().stream().map(User::new).toList();
    }


    @Transactional
    public ResponseEntity<?> deleteUser(Long id) throws Exception {

        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado correctamente");
            }
        }catch (Exception e){
                throw new Exception(e.getMessage());
            }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no existe");
    }

    public ResponseEntity<?> save(User user) throws Exception {
        try {
            if (!userRepository.existsById(user.getDni())) {
                User s = userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("Usuario agregado");
            }
        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no pudo ser agregado");
    }

    @Transactional
    public ResponseEntity<?> disableAccount(Long userId) throws Exception {

        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setState(DISABLED);
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("Cuenta deshabilitada correctamente");
            }
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no existe");
    }



    @Transactional
    public ResponseEntity<?> enableAccount(Long userId) throws Exception {

        try {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setState(AVALIABLE);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("Cuenta Habilitada correctamente");
        }
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no existe");
    }

    @Transactional(readOnly = true)
    public User getUserById(Long userId) throws Exception {

        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            return optionalUser.orElse(null);
        }catch (Exception e){
        throw  new Exception(e.getMessage());
    }

}




}
