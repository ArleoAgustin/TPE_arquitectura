package app.Controller;

import app.DTO.AdminDTO;
import app.model.entities.Admin;
import app.security.AuthorityConstants;
import app.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminControllerJPA {


    private final AdminService adminService;

//obtiene todos los administradores

    @GetMapping("")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.ADMIN + "\")" )
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
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.ADMIN + "\")" )
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
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> addAdmin(@RequestBody Admin admin) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(adminService.save(admin));
    }

//agrega un rol
//agregar solo el nombre en formato txt
    @PostMapping("/addRol")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> addRol(@RequestBody String nameRol) throws Exception {

        return adminService.addRol(nameRol);

    }


//elimina un admin

    @DeleteMapping("/{id_admin}")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id_admin) throws Exception {

        return adminService.delete(id_admin);

    }

//elimina un rol

    @DeleteMapping("/deleteRol/{id_rol}")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> deleteRol(@PathVariable Long id_rol) throws Exception {

        return adminService.deleteRol(id_rol);
    }

//Actualiza un admin

    @PutMapping("/{id_admin}")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> updateAdmin(@PathVariable Long id_admin, @RequestBody Admin updateAdmin) throws Exception {

        return adminService.update(id_admin,updateAdmin);
    }


}
