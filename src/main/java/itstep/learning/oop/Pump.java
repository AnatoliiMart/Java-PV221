package itstep.learning.oop;

import java.util.Locale;

@Warranty(2)
public class Pump
        extends Product
        implements Manual{
    int Productivity;

    public Pump(String manufacturer,int productivity) {
        this.setProductivity(productivity);
        super.setManufacturer(manufacturer);
    }

    public int getProductivity() {
        return Productivity;
    }

    public void setProductivity(int productivity) {
        Productivity = productivity;
    }
    @Override
    public String getCard() {
        return String.format(
                Locale.ROOT,
                "Pump: '%s', Productivity: '%dl/h'",
                super.getManufacturer(), this.getProductivity()
        );
    }
    @Works("as pump")
    public void pump() {
        System.out.println("pump: " + this.getCard());
    }
}
