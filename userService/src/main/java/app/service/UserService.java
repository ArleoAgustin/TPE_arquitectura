package app.service;

import app.repository.UserRepository;
import app.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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


    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }

    public User updateUser(Long userId, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setName(updatedUser.getName());
            userToUpdate.setLastName(updatedUser.getLastName());
            userToUpdate.setEmail(updatedUser.getEmail());
            userToUpdate.setNumberPhone(updatedUser.getNumberPhone());
            userToUpdate.setStartDate(updatedUser.getStartDate());
            return userRepository.save(userToUpdate);
        }
        return null;
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
