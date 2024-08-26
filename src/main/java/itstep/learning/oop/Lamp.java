package itstep.learning.oop;

import java.util.Locale;

@Warranty(3)
public class Lamp
        extends Product
        implements Testable{
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

    @Override
    public void test() {
        System.out.println("Testing: " + this.getCard());
    }

    @Works("as lamp")

    public void shine() {
        System.out.println("Shine: " + this.getCard());
    }
}
