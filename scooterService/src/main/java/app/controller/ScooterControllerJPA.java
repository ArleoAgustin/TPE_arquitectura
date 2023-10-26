package app.controller;


import app.model.Scooter;
import app.service.ScooterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("scooter")
@RequiredArgsConstructor
public class ScooterControllerJPA {

    private final ScooterService scooterService;


    @GetMapping("")
    public ResponseEntity<ResponseEntity> findAll() {
        List<Scooter> scooterList = this.scooterService.findAll();
        if (scooterList.isEmpty()){
            return ResponseEntity.status(204).body((ResponseEntity)scooterList);
        }
        return ResponseEntity.status(HttpStatus.OK).body((ResponseEntity)scooterList);
    }

    @GetMapping("/scooter/inMaintenance")
    public ResponseEntity<ResponseEntity> getScooterinMaintenance(){
        return ResponseEntity.status(HttpStatus.OK).body((ResponseEntity) scooterService.getScooterInMaintenance());
    }
/*

    @PutMapping("/setPrice")
    public ResponseEntity<ResponseEntity> setPrice(@PathVariable Tariff tariff){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.setNewTariff(tariff));
    }

    @PutMapping("/addScooterMaintenance/{id_scooter}")
    public ResponseEntity<ResponseEntity> addScooterToMaintenance(@PathVariable Long id_scooter){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.addScooterToMaintenance(id_scooter));
    }

    @PutMapping("/removeScooterMaintenance/{id_scooter}")
    public ResponseEntity<ResponseEntity> removeScooterToMaintenance(@PathVariable Long id_scooter){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.removeScooterOfMaintenance(id_scooter));
    }

    @DeleteMapping("/scooter/{scooter_id}")
    public ResponseEntity<String> deleteScooter(@PathVariable Long scooter_id){
        return adminService.deleteScooter(scooter_id);
    }

     @PutMapping("/updateAdmin/{id_admin}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id_admin, @RequestBody Admin updateAdmin){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.update(id_admin,updateAdmin));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el administrador");
        }

    }
*/

    @PostMapping("")
    public ResponseEntity<?> addScooter(@RequestBody Scooter scooter){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.save(scooter));
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo agregar el monopatin");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id){
        try {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(scooterService.delete(id));

        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo eliminar el administrador");
        }
    }


}
