package app.Controller;

import app.DTO.TariffDTO;
import app.model.entities.Tariff;
import app.service.TariffService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Parameter;
import java.util.List;

@RestController
@RequestMapping("tariff")
@RequiredArgsConstructor
public class TariffControllerJPA {

    private final TariffService tariffService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        List<Tariff> tariffs = tariffService.findAll();
        if (!tariffs.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(tariffService.findAll());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay tarifas");

    }

    @PostMapping("")
    public ResponseEntity<?> addTariff(@RequestBody Tariff tariff) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tariffService.save(tariff));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al insertar una tarifa");
        }
    }

    //ajusta la tarifa

    @PutMapping("/adjustmentPrice/{id_tariff}")
    public ResponseEntity<?> adjustmentPrice(@RequestBody Tariff newTariff, @PathVariable Long id_tariff){
        return ResponseEntity.status(HttpStatus.OK).body(tariffService.adjustmentPrice(newTariff, id_tariff));
    }

    @GetMapping("/currentPrice")
    public ResponseEntity<?> currentPrice(){
        if (tariffService.getTariffActive() != null)
            return ResponseEntity.status(HttpStatus.OK).body(tariffService.getTariffActive());
        else
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay tarifa activa");
    }

    @DeleteMapping("/{id_tariff}")
    public ResponseEntity<?> delete(@PathVariable Long id_tariff){

        return tariffService.delete(id_tariff);

    }

}
