package app.Controller;

import app.model.entities.Admin;
import app.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminControllerJPA {

    private final AdminService adminService;

//obtiene todos los administradores

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try{
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener todos los administradores.");
        }
    }

//obtiene todos los roles

    @GetMapping("/getAllRol")
    public ResponseEntity<?> findAllRol() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllRol());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener todos los roles.");
        }
    }


//agrega un admin

    @PostMapping("")
    public ResponseEntity<?> addAdmin(@RequestBody Admin admin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(adminService.save(admin));
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo agregar el administrador");
        }
    }

//agrega un rol

    @PostMapping("/addRol")
    public ResponseEntity<?> addRol(@RequestBody String nameRol){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(adminService.addRol(nameRol));
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo agregar el rol");
        }
    }


//elimina un admin

    @DeleteMapping("/{id_admin}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id_admin){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.delete(id_admin));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo eliminar el administrador");
        }
    }

//elimina un rol

    @DeleteMapping("/deleteRol/{id_rol}")
    public ResponseEntity<?> deleteRol(@PathVariable Long id_rol){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteRol(id_rol));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo eliminar el administrador");
        }
    }

//Actualiza un admin

    @PutMapping("/{id_admin}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id_admin, @RequestBody Admin updateAdmin){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.update(id_admin,updateAdmin));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el administrador");
        }
    }


}
