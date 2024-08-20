package itstep.learning.oop;

import java.util.Locale;

public class Accumulator extends Product {
  private int Capacity;

    public Accumulator(String manufacturer, int capacity) {
       super.setManufacturer(manufacturer);
       this.setCapacity(capacity);
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    @Override
    public String getCard() {
        return String.format(
                Locale.ROOT,
                "Accumulator: '%s', Productivity: '%dA'",
                super.getManufacturer(), this.getCapacity()
        );
    }
}
