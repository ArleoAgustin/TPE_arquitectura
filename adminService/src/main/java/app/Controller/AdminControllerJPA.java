package app.Controller;

import app.DTO.AdminDTO;
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
            List<AdminDTO> admins = adminService.findAll();
            if (!admins.isEmpty())
                return ResponseEntity.status(HttpStatus.OK).body(admins);
            else
                return ResponseEntity.status(204).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener todos los administradores.");
        }
    }

//obtiene todos los roles

    @GetMapping("/getAllRol")
    public ResponseEntity<?> findAllRol() {
        try{
            List roles = adminService.getAllRol();
            if (!roles.isEmpty())
                return ResponseEntity.status(HttpStatus.OK).body(roles);
            else
                return ResponseEntity.status(204).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener todos los roles.");
        }
    }


//agrega un admin

    @PostMapping("")
    public ResponseEntity<?> addAdmin(@RequestBody Admin admin) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(adminService.save(admin));
    }

//agrega un rol
//agregar solo el nombre en formato txt
    @PostMapping("/addRol")
    public ResponseEntity<?> addRol(@RequestBody String nameRol) throws Exception {

        return adminService.addRol(nameRol);

    }


//elimina un admin

    @DeleteMapping("/{id_admin}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id_admin) throws Exception {

        return adminService.delete(id_admin);

    }

//elimina un rol

    @DeleteMapping("/deleteRol/{id_rol}")
    public ResponseEntity<?> deleteRol(@PathVariable Long id_rol) throws Exception {

        return adminService.deleteRol(id_rol);
    }

//Actualiza un admin

    @PutMapping("/{id_admin}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id_admin, @RequestBody Admin updateAdmin) throws Exception {

        return adminService.update(id_admin,updateAdmin);
    }


}
