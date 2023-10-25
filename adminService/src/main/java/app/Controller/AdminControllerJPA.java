package app.Controller;

import app.DTO.AdminDTO;
import app.model.classs.Scooter;
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

    @GetMapping("")
    public List<AdminDTO> findAll() {
        return this.adminService.findAll();
    }

    @GetMapping("/scooter/inMaintenance")
    public ResponseEntity<ResponseEntity> getScooterinMaintenance(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getScooterInMaintenance());
    }

    @PostMapping("/addScooterMaintenance/{id_scooter}")
    public ResponseEntity<ResponseEntity> addScooter(@PathVariable Long id_scooter){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.setScooterToMaintenance(id_scooter));
    }

    @DeleteMapping("/scooter/{scooter_id}")
    public ResponseEntity<String> deleteScooter(@PathVariable Long scooter_id){
        return adminService.deleteScooter(scooter_id);
    }

    @PostMapping("/addScooter")
    public ResponseEntity<?> addAdmin(@RequestBody Scooter scooter){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(adminService.addScooter(scooter));
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo agregar el monopatin");
        }
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<?> addAdmin(@RequestBody Admin admin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(adminService.save(admin));
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo agregar el administrador");
        }
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id_admin){
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(adminService.delete(id_admin));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo eliminar el administrador");
        }
    }

    @PutMapping("/updateAdmin/{id_admin}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id_admin, @RequestBody Admin updateAdmin){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.update(id_admin,updateAdmin))
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el administrador");
        }

    }
}
