package app.controllers;

import app.model.entities.Travel;
import app.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("travel")
@RequiredArgsConstructor
public class TravelContollerJPA {

    private final TravelService travelService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<Travel> travels = this.travelService.findAll();
        return travels.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(travels) : ResponseEntity.status(HttpStatus.OK).body(travels);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTravel(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(travelService.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo eliminar el viaje");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(travelService.getById(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se encontro el viaje");
        }
    }

//REVISAR
    @PostMapping("")
    public ResponseEntity<?> addTravel(@RequestBody Long userDNI, @RequestBody Long scooterId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(travelService.addTravel(userDNI, scooterId));
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Error: no se pudo agregar el viaje");
        }
    }

//Como administrador quiero consultar los monopatines con más de X viajes en un cierto año.

    @GetMapping("/scootersWithMoreThanXin/{numTravels}/{year}")
    public  ResponseEntity<?> getScootersByTravelsInYear(@PathVariable Integer numTravels, @PathVariable Integer year){
        try {
            List<Long> scootersId = travelService.getScootersWithMoreThanTravelsInYear(numTravels, year);
            if (scootersId == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no se encontraron monopatines con viajes en ese anio");
            return ResponseEntity.status(HttpStatus.OK).body(scootersId);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("no se encontraron monopatines con viajes en ese anio");
        }
    }

    //obtiene lo facturado en un rango de meses de un determinado año
    @GetMapping("/getTotalBillingBetween")
    public ResponseEntity<?> getTotalBillingBetween(@RequestParam("month1") Integer month1, @RequestParam("month2") Integer month2, @RequestParam("year") Integer year){
        return ResponseEntity.status(HttpStatus.OK).body(travelService.getBillingBetweenIn(year, month1, month2));
    }

}
