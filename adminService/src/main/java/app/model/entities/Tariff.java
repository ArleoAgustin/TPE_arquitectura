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
    private String nameTariff;

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

    public Tariff(Tariff t){
        this.nameTariff = t.getNameTariff();
        this.price = t.getPrice();
        this.pricePauseExt = t.getPricePauseExt();
        this.effectiveDate = t.getEffectiveDate();
    }

    public Long getId() {
        return id;
    }

    public String getNameTariff() {
        return nameTariff;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPricePauseExt() {
        return pricePauseExt;
    }

    public void setPricePauseExt(Double pricePauseExt) {
        this.pricePauseExt = pricePauseExt;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
