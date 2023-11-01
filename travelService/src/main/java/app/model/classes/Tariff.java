package app.model.classes;

import java.io.Serializable;

public class Tariff implements Serializable {

    private String name;
    private double price;
    private double pricePauseExt;

    public Tariff(String name, double price, double pricePauseExt) {
        this.name = name;
        this.price = price;
        this.pricePauseExt = pricePauseExt;
    }

    public Tariff() {

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getPricePauseExt() {
        return pricePauseExt;
    }
}
