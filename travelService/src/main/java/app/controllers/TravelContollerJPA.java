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

    @PostMapping("/add")
    public ResponseEntity<?> addTravel(@RequestBody Travel travel) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(travelService.addTravel(travel));
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Error: no se pudo agregar el viaje");
        }

    }

}
/*todo:
 @GetMapping("/scootersByTravels/{numTravels}/{year}")
    public  ResponseEntity<?> getScootersByTravelsInYear(@PathVariable int numTravels, @PathVariable String year){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getScootersByTravelsInYear(numTravels, year));
    }
*/