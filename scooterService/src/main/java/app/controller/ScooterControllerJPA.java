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
    public ResponseEntity<?> findAll() {
        List<Scooter> scooterList = this.scooterService.findAll();
        if (scooterList.isEmpty()){
            return ResponseEntity.status(204).build();
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
        return ResponseEntity.status(HttpStatus.OK).body(scooterService.getScooterInMaintenance());
    }

    @PutMapping("/addScooterMaintenance/{id_scooter}")
    public ResponseEntity<?> addScooterToMaintenance(@PathVariable Long id_scooter){
        Scooter isChanged = (Scooter) scooterService.addScooterToMaintenance(id_scooter);
        if(isChanged != null){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se pudo agregar a mantenimiento");
    }
    /*
        @GetMapping("/getByTravelsInYear/{numTravels}/{year}")
        public ResponseEntity<?> getScootersByTravelsInYear(@PathVariable Integer numTravels, @PathVariable Integer year) {
            List<Scooter> scooterList = scooterService.getScootersWithMoreThanTravelsInYear(numTravels,year);
            if(scooterList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no hay monopatines con mas de "+numTravels+" en el anio "+year);
            }
            else{
                return ResponseEntity.status(HttpStatus.OK).bodscooterList);
            }
        }
    /*
        TODO: "http://localhost:8083/scooters/getByTravelsInYear/"+ numTravels + "/" + year, y tiene que devolver esto Como administrador quiero consultar los monopatines con más de X viajes en un cierto año.

    */
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


}
