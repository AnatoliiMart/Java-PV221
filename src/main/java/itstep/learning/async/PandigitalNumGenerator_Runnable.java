package itstep.learning.async;

public class PandigitalNumGenerator_Runnable {

    private final int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int currentIndex = 0;
    private final StringBuilder buffer = new StringBuilder();

    public void generate() throws InterruptedException {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    addDigit();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < 10; i++)
            threads[i].join();

        System.out.println("Pandigital number RUNNABLE: " + buffer.toString());
    }

    private synchronized void addDigit() {
        if (currentIndex < digits.length) {
            buffer.append(digits[currentIndex]);
            currentIndex++;
        }
    }
}
