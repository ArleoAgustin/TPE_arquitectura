package app.Controller;

import app.DTO.TariffDTO;
import app.model.entities.Tariff;
import app.service.TariffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tariff")
@RequiredArgsConstructor
public class TariffControllerJPA {

    private final TariffService tariffService;

    @PutMapping("")
    public ResponseEntity<?> addTariff(@PathVariable Tariff tariff) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tariffService.save(tariff));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al insertar una tarifa");
        }

    }
}
