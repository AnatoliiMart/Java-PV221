package itstep.learning.oop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    private final List<Product> products;

    public Shop() {
        products = new ArrayList<>();
        products.add(new Lamp("Philips", 60.0));
        products.add(new Pump("Pumper", 100));
        products.add(new Accumulator("Varta", 75));
    }
    private void printProducts() {
        for (Product product : products) {
            if (product instanceof Testable) {
                ((Testable) product).test();
            }
            else{
                System.out.println(product.getCard());
            }

        }
    }
    private void printManualProducts() {
        for (Product product : products) {
            if (product instanceof Manual) {
                System.out.println(product.getCard());
            }
        }
    }
    private void printNonManualProducts() {
        for (Product product : products) {
            if (!(product instanceof Manual)) {
                System.out.println(product.getCard());
            }
        }
    }
    public void run() {
        this.printProducts();
        System.out.println("-----------------------MANUAL-----------------------");
        this.printManualProducts();
        System.out.println("---------------------NON-MANUAL---------------------");
        this.printNonManualProducts();
        System.out.println("-----------------------WORKS------------------------");
        showWorks();
        System.out.println("---------------------WARRANTY-----------------------");
        showWarranty();
    }
    private void showWorks(){
        for (Product product : products) {
            // Get the method what was marked by "Works" annotation and call it
            for(Method method : product.getClass().getDeclaredMethods()){
                if(method.isAnnotationPresent(Works.class)){
                    System.out.print(method.getAnnotation(Works.class).value() + " ");
                    method.setAccessible(true);
                    try {
                        method.invoke(product);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                          System.err.println(e.getMessage());
                    }
                }
            }
        }
    }
    private void showWarranty(){
        for (Product product : products) {
            if (product.getClass().isAnnotationPresent(Warranty.class)) {
                System.out.println(
                        "Warranty valid "
                        + product.getClass().getAnnotation(Warranty.class).value()
                        + " years. "
                        + product.getCard());
            }
            else
            {
                System.out.println("No warranty available. " + product.getCard());
            }

        }
    }
}
/*
Shop <>--------------->Product
                    (manufacturer)
                          △
                        / | \
             Accumulator  |  Pump
             (capacity)  Lamp   (productivity)
                        (power)
Accumulator(manufacturer, capacity)
Lamp(manufacturer, power)
Pump(manufacturer, productivity)

*/
