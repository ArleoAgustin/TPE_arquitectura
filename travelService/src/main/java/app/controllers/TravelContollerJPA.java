package app.controllers;

import app.model.classes.Scooter;
import app.model.entities.Travel;
import app.security.AuthorityConstants;
import app.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/travel")
@RequiredArgsConstructor
public class TravelContollerJPA {

    private final TravelService travelService;

    @GetMapping("")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.USER + "\" , \"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> findAll() {

        List<Travel> travels = this.travelService.findAll();

        if (!travels.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(travels);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: no hay viajes");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.USER + "\" , \"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> deleteTravel(@PathVariable Long id) throws Exception {

        return travelService.delete(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.USER + "\" , \"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> getById(@PathVariable Long id) throws Exception {

        Travel t = travelService.getById(id);
        if (t != null)
            return ResponseEntity.status(HttpStatus.OK).body(t);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: no se encontro el viaje");
    }

    @PostMapping("")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.USER + "\" , \"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> addTravel(@RequestParam Long userDNI, @RequestParam Long scooterId, @RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.replace("Bearer ", "");
        return travelService.addTravel(userDNI, scooterId, token);
    }

//Como administrador quiero consultar los monopatines con más de X viajes en un cierto año.

    @GetMapping("/scootersWithMoreThanXin")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.USER + "\" , \"" + AuthorityConstants.ADMIN + "\")" )
    public  ResponseEntity<?> getScootersByTravelsInYear(@RequestParam Integer numTravels, @RequestParam Integer year, @RequestHeader("Authorization") String authorizationHeader) throws Exception {

        String token = authorizationHeader.replace("Bearer ", "");

            List<Scooter> scooters = travelService.getScootersWithMoreThanTravelsInYear(numTravels, year, token);
            if (scooters == null)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no se encontraron monopatines con viajes en ese anio");
            else
                return ResponseEntity.status(HttpStatus.OK).body(scooters);
    }


    //obtiene lo facturado en un rango de meses de un determinado año
    @GetMapping("/getTotalBillingBetween")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.USER + "\" , \"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> getTotalBillingBetween(@RequestParam("month1") Integer month1,
                                                    @RequestParam("month2") Integer month2,
                                                    @RequestParam("year") Integer year){
        return ResponseEntity.status(HttpStatus.OK).body(travelService.getBillingBetweenIn(year, month1, month2));
    }

}
