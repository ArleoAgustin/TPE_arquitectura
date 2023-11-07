package app.controllers;

import app.model.User;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserControllerJPA {

    private final UserService userService;

//trae todos los usuarios

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<User> userList = this.userService.findAll();
        if (userList.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

//agrega un usuario

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody User user) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }

//elimina un usuario

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) throws Exception {

        return userService.deleteUser(userId);
    }

//deshabilita una cuenta

    @PutMapping("/disableAccount/{userId}")
    public ResponseEntity<?> disableAccount(@PathVariable Long userId) throws Exception {

            return userService.disableAccount(userId);
    }

//habilita una cuenta

    @PutMapping("/enableAccount/{userId}")
    public ResponseEntity<?> enableAccount(@PathVariable Long userId) throws Exception {
        return userService.enableAccount(userId);
    }

//obtiene un usuario por id

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) throws Exception {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user); // Usuario encontrado, se devuelve en la respuesta
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no existe");
        }
    }

}
