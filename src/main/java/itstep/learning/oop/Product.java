package itstep.learning.oop;

public abstract class Product {
    private String Manufacturer;

    public abstract String getCard();

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
                        Manufacturer = manufacturer;
    }
}
