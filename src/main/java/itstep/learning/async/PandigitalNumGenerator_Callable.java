package itstep.learning.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PandigitalNumGenerator_Callable {

    private final int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int currentIndex = 0;
    private final StringBuilder buffer = new StringBuilder();

    public void generate() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<Void>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() {
                    addDigit();
                    return null;
                }
            };
            futures.add(executor.submit(task));
        }

        for (Future<Void> future : futures)
            future.get();

        executor.shutdown();
        System.out.println("Pandigital number CALLABLE: " + buffer.toString());
    }

    private synchronized void addDigit() {
        if (currentIndex < digits.length) {
            buffer.append(digits[currentIndex]);
            currentIndex++;
        }
    }
}

