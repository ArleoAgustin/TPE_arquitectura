package app.Controller;

import app.DTO.AdminDTO;
import app.model.classs.Scooter;
import app.model.entities.Admin;
import app.model.entities.Tariff;
import app.service.AdminService;
import app.service.TariffService;
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
    private final TariffService tariffService;

//obtiene todos los administradores

    @GetMapping("")
    public List<AdminDTO> findAll() {
        return this.adminService.findAll();
    }

//ajusta la tarifa

    @PutMapping("/adjustmentPrice")
    public ResponseEntity<?> removeScooterToMaintenance(@RequestBody Tariff newTariff) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(tariffService.save(newTariff));// va en tariff controler
    }


//Como administrador quiero consultar los monopatines con más de X viajes en un cierto año.

    @GetMapping("/scootersByTravels/{numTravels}/{year}")
    public  ResponseEntity<?> getScootersByTravelsInYear(@PathVariable int numTravels, @PathVariable String year){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getScootersByTravelsInYear(numTravels, year));
    }//va en scoter controller


//obtiene todos los monopatines en mantenimiento

    @GetMapping("/scooter/inMaintenance")
    public ResponseEntity<?> getScooterinMaintenance(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getScooterInMaintenance());
    }//va en scoter controller


//agrega un monopatin de mantenimiento

    @PutMapping("/addScooterMaintenance/{id_scooter}")
    public ResponseEntity<?> addScooterToMaintenance(@PathVariable Long id_scooter){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.addScooterToMaintenance(id_scooter));
    }//va en scoter controller


//quita un monopatin de mantenimiento

    @PutMapping("/removeScooterMaintenance/{id_scooter}")
    public ResponseEntity<?> removeScooterToMaintenance(@PathVariable Long id_scooter){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.removeScooterOfMaintenance(id_scooter));
    }//va en scoter controller


//elimina un monopatin

    @DeleteMapping("/scooter/{scooter_id}")
    public ResponseEntity<?> deleteScooter(@PathVariable Long scooter_id){
        return adminService.deleteScooter(scooter_id);
    }//va en scoter controller


//agrega un monopatin

    @PostMapping("/addScooter")
    public ResponseEntity<?> addScooter(@RequestBody Scooter scooter){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(adminService.addScooter(scooter));
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo agregar el monopatin");
        }//va en scoter controller
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


//elimina un admin

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id_admin){
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(adminService.delete(id_admin));
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


//habilita una cuenta de usuario

    @PutMapping("/modifyAvaibleAccount/{id_user}")
    public ResponseEntity<?> modifyAvaibleAccount(@PathVariable Long id_user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.avaibleUserAccount(id_user));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el administrador");
        }
    }


//Desabilita una cuenta de usuario

    @PutMapping("/modifyDisableAccount/{id_user}")
    public ResponseEntity<?> modifyDisableAccount(@PathVariable Long id_user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.disableUserAccount(id_user));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el administrador");
        }
    }


//obtiene lo facturado en un rango de meses de un determinado año

    @GetMapping("/getTotalBilling/{month1}/{month2}/{year}")
    public ResponseEntity<?> getBilling_Xmonths_Xyear(@PathVariable String month1, @PathVariable String month2, @PathVariable String year){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getBilling_Xmonths_Xyear(month1, month2, year));
    }


//obtiene un listado de monopatines en mantenimiento vs los disponibles

    @GetMapping("/scootersInMaintenanceVsAvaible")
    public ResponseEntity<?> getScootersInMaintenanceVsAvaible(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getScootersInMaintenanceVsAvaible());
    }
}
