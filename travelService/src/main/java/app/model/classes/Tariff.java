package app.model.classes;

import java.io.Serializable;

public class Tariff implements Serializable {

    private final String name;
    private final double price;
    private final double pricePauseExt;

    public Tariff(String name, double price, double pricePauseExt) {
        this.name = name;
        this.price = price;
        this.pricePauseExt = pricePauseExt;
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
