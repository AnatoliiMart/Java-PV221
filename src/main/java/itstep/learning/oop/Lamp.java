package itstep.learning.oop;

import java.util.Locale;

public class Lamp extends Product{
    private double Power;

    public Lamp(String manufacturer, double power) {

        super.setManufacturer(manufacturer);
        this.setPower(power);
    }

    public double getPower() {
        return Power;
    }

    public void setPower(double power) {
        if (power < 0) {
            throw new RuntimeException("Power can't be less than 0");
        }
        this.Power = power;
    }


    @Override
    public String getCard() {
        return String.format(
                Locale.ROOT,
                "Lamp: '%s', Power: '%.1fW'",
                super.getManufacturer(), this.getPower()
        );
    }
}
