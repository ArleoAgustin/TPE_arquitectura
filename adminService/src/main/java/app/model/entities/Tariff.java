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
    private Double value;

    @Column
    private Double valuePauseExt;

    @Column
    private Date effectiveDate; //fecha en la que entra en vigencia

    public Tariff() {
    }

    public Tariff(Double value, Double valuePauseExt, Date effectiveDate) {
        this.value = value;
        this.valuePauseExt = valuePauseExt;
        this.effectiveDate = effectiveDate;
    }
}
