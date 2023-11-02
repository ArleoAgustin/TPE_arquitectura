package app.controller;


import app.DTOs.ScooterReportByKm;
import app.model.Scooter;
import app.service.ScooterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("scooter")
@RequiredArgsConstructor
public class ScooterControllerJPA {

    private final ScooterService scooterService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<Scooter> scooterList = this.scooterService.findAll();
        if (scooterList.isEmpty()){
            return ResponseEntity.status(204).body("no se encontraron resultados");
        }
        return ResponseEntity.status(HttpStatus.OK).body(scooterList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Scooter result = scooterService.getById(id);
        if(result == null)return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no se encontro el monopatin");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/inMaintenance")
    public ResponseEntity<?> getScooterinMaintenance(){
        return ResponseEntity.status(HttpStatus.OK).body(scooterService.getScooterByStatus(Scooter.IN_MANTENIENCE));
    }

    @PutMapping("/addScooterMaintenance/{id_scooter}")
    public ResponseEntity<?> addScooterToMaintenance(@PathVariable Long id_scooter){
        Scooter isChanged = (Scooter) scooterService.addScooterToMaintenance(id_scooter);
        if(isChanged != null){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se pudo agregar a mantenimiento");
    }

    @PutMapping("/removeScooterMaintenance/{id_scooter}")
    public ResponseEntity<?> removeScooterToMaintenance(@PathVariable Long id_scooter){
        return ResponseEntity.status(HttpStatus.OK).body(scooterService.removeScooterOfMaintenance(id_scooter));
    }

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
    public ResponseEntity<?> deleteScooter(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(scooterService.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo eliminar el monopatineta");
        }
    }

    @GetMapping("/byStatus")
    public ResponseEntity<?> getScootersByStatus(){
        HashMap<String,List<Scooter>> scooters = new HashMap<>();
        try {
            scooters.put("En uso",this.scooterService.getScooterByStatus(Scooter.IN_USE));
            scooters.put("En mantenimiento",this.scooterService.getScooterByStatus(Scooter.IN_MANTENIENCE));
            scooters.put("disponibles",this.scooterService.getScooterByStatus(Scooter.AVALIABLE));
            scooters.put("desabilitados",this.scooterService.getScooterByStatus(Scooter.DISABLED));
            return ResponseEntity.status(HttpStatus.OK).body(scooters);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("servicio no disponible");
        }
    }

    /*generar un reporte de uso de monopatines por kilómetros para establecer si un monopatín requiere de mantenimiento.
     Este reporte debe poder configurarse para incluir (o no) los tiempos de pausa*/

    @GetMapping("/reportBykms/{kms}")
    public ResponseEntity<?> getReportForKms(@RequestParam Boolean include, @PathVariable double kms) {
        try {
            List<ScooterReportByKm> scooters = this.scooterService.getReportForKm(include, kms);
            if (scooters.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no se encontraron monopatines");
            }
            return ResponseEntity.status(HttpStatus.OK).body(scooters);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("servicio no disponible");
        }

    }

    @GetMapping("/getNearby")
    public ResponseEntity<?> getNearby(@RequestParam String ubication){
        try {
            List<Scooter> scooters = this.scooterService.getNearby(ubication);
            if (scooters.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no se encontraron monopatines");
            }
            return ResponseEntity.status(HttpStatus.OK).body(scooters);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("servicio no disponible");
        }

    }


    @GetMapping("/getAllByIds")
    public ResponseEntity<?> getViajesPorIds(@RequestParam List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            List<Scooter> scooters = scooterService.getListScooter(ids);
            if (!scooters.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(scooters);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron monopatines");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lista de IDs vacía o nula");
        }
    }

}
