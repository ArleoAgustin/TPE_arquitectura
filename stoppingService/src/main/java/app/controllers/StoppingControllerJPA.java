package app.controllers;

import app.model.Stopping;
import app.model.classes.Scooter;
import app.security.AuthorityConstants;
import app.service.StoppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/stopping")
@RequiredArgsConstructor
public class StoppingControllerJPA {

    private final StoppingService stoppingService;

//obtiene todas las paradas

    @GetMapping("")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.USER + "\" , \"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> findAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(stoppingService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener todas las paradas.");
        }
    }

//agrega una parada

    @PostMapping("")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.USER + "\" , \"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> addStopping(@RequestParam String ubication){

        return stoppingService.save(ubication);

    }


//elimina una parada

    @DeleteMapping("/{id_stopping}")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.USER + "\" , \"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> deleteStopping(@PathVariable Long id_stopping){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(stoppingService.delete(id_stopping));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar la parada");
        }
    }

//Actualiza una parada

    @PutMapping("/{id_stopping}")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.USER + "\" , \"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> updateStopping(@PathVariable Long id_stopping, @RequestBody Stopping updateStopping){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(stoppingService.update(id_stopping,updateStopping));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la parada");
        }
    }

//agrega un monopatin a la parada

    @PostMapping("/addScooter/{id_stopping}")
    @PreAuthorize( "hasAnyAuthority(\"" + AuthorityConstants.USER + "\" , \"" + AuthorityConstants.ADMIN + "\")" )
    public ResponseEntity<?> addScooter(@RequestBody Scooter scooter, @PathVariable Long id_stopping){

        return stoppingService.addScooter(id_stopping, scooter);

    }
}
