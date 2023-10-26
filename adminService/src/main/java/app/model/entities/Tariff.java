package app.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double price;

    @Column
    private Double pricePauseExt;

    @Column
    private Date effectiveDate; //fecha en la que entra en vigencia

    public Tariff() {
    }

    public Tariff(Double value, Double valuePauseExt, Date effectiveDate) {
        this.price = value;
        this.pricePauseExt = valuePauseExt;
        this.effectiveDate = effectiveDate;
    }
}
