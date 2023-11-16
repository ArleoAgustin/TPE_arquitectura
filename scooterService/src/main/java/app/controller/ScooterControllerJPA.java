package app.controller;


import app.DTOs.ScooterReportByKm;
import app.model.Scooter;
import app.service.ScooterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Data
@RestController
@RequestMapping("api/scooter")
@RequiredArgsConstructor
@Tag(name = "Scooter", description = "Scooter services")
public class ScooterControllerJPA {

    private final ScooterService scooterService;

    @Operation(summary = "Get all scooters")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<Scooter> scooterList = this.scooterService.findAll();
        if (scooterList.isEmpty()){
            return ResponseEntity.status(204).body("no se encontraron resultados");
        }
        return ResponseEntity.status(HttpStatus.OK).body(scooterList);
    }

    @Operation(summary = "Get scooters by ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Scooter result = scooterService.getById(id);
        if(result == null)return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no se encontro el monopatin");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Get scooters in maintenance")
    @GetMapping("/inMaintenance")
    public ResponseEntity<?> getScooterinMaintenance(){
        return ResponseEntity.status(HttpStatus.OK).body(scooterService.getScooterByStatus(Scooter.IN_MANTENIENCE));
    }

    @Operation(summary = "Add scooter to maintenance")
    @PutMapping("/addScooterMaintenance/{id_scooter}")
    public ResponseEntity<?> addScooterToMaintenance(@PathVariable String id_scooter){
        Scooter isChanged = (Scooter) scooterService.addScooterToMaintenance(id_scooter);
        if(isChanged != null){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se pudo agregar a mantenimiento");
    }

    @Operation(summary = "Remove scooter to maintenance")
    @PutMapping("/removeScooterMaintenance/{id_scooter}")
    public ResponseEntity<?> removeScooterToMaintenance(@PathVariable String id_scooter){
        return ResponseEntity.status(HttpStatus.OK).body(scooterService.removeScooterOfMaintenance(id_scooter));
    }

    @Operation(summary = "Add scooter")
    @PostMapping("")
    public ResponseEntity<?> addScooter(@RequestBody Scooter scooter){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.save(scooter));
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo agregar el monopatin");
        }
    }

    @Operation(summary = "Delete scooter")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScooter(@PathVariable String id){
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(scooterService.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo eliminar el monopatineta");
        }
    }

    @Operation(summary = "Get cant scooters in maintenance vs avaliables")
    @GetMapping("/inMantenianceVsAvaliable")
    public ResponseEntity<?> inMantenianceVsAvaliable(){
        HashMap<String,Integer> scooters = new HashMap<>();
        try {
            scooters.put("En mantenimiento", scooterService.getScooterByStatus(Scooter.IN_MANTENIENCE).size());
            scooters.put("Disponibles", scooterService.getScooterByStatus(Scooter.AVALIABLE).size());
            return ResponseEntity.status(HttpStatus.OK).body(scooters);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("servicio no disponible");
        }
    }

    @Operation(summary = "Get scooters by status")
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

    @Operation(summary = "Get scooters report for kms or kms/pauses")
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

    @Operation(summary = "Get scooters by ubication")
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

    @Operation(summary = "Get scooters by ids")
    @GetMapping("/getAllByIds")
    public ResponseEntity<?> getViajesPorIds(@RequestParam List<String> ids) {
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
