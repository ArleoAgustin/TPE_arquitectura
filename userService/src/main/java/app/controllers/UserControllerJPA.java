package app.controllers;

import app.model.User;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("users")
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
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        try {
            boolean deleted = userService.deleteUser(userId);
            if (deleted) {
                return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario");
        }
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

    //Actualizar usuario
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        try {
            User user = userService.updateUser(userId, updatedUser);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el usuario");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al tratar de actualizar el usuario");
        }
    }

//obtiene un usuario por id
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no fue encontrado");
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al tratar de acceder a la base de datos");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error");
        }
    }

}
