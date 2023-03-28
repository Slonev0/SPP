package org.example;

public class MultiThreadCounter1 {
    static long sharedVariable = 0;

    public static void main(String[] args) throws InterruptedException {
        final int NUM_INCREMENT_THREADS = 5;
        final int NUM_READ_THREADS = 15;
        final int NUM_ITERATIONS = 100000;
        final int PRINT_EVERY = 20000;

        for (int i = 0; i < NUM_INCREMENT_THREADS; i++) {
            new Thread(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    sharedVariable++;
                }
            }).start();
        }

        for (int i = 0; i < NUM_READ_THREADS; i++) {
            int id = i;
            new Thread(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    if (j % PRINT_EVERY == 0) {
                        System.out.println("Read thread " + id + " read value: " + sharedVariable);
                    }
                }
            }).start();
        }

        Thread.sleep(5000);
    }
}
