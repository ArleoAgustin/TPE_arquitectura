package app.controllers;

import app.model.User;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserControllerJPA {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<User> userList = this.userService.findAll();
        if (userList.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo agregar el usuario");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo eliminar el usuario");
        }
    }

    @PutMapping("/disableAccount/{userId}")
    public ResponseEntity<?> disableAccount(@PathVariable Long userId){
        User isChanged = (User) userService.disableAccount(userId);
        if(isChanged != null){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error:No se pudo inhabilitar al usuario");
    }

    @PutMapping("/enableAccount/{userId}")
    public ResponseEntity<?> enableAccount(@PathVariable Long userId){
        User isChanged = (User) userService.enableAccount(userId);
        if(isChanged != null){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error:No se pudo habilitar al usuario");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user); // Usuario encontrado, se devuelve en la respuesta
        } else {
            return ResponseEntity.notFound().build(); // Usuario no encontrado
        }
    }



}
