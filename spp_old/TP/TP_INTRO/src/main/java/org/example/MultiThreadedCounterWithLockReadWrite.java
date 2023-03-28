package org.example;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultiThreadedCounterWithLockReadWrite {
    private static final int NUM_ITERATIONS = 100000;
    private static final int PRINT_FREQUENCY = 20000;

    private static long sharedVariable = 0;
    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        Thread[] incrementThreads = new Thread[5];
        for (int i = 0; i < incrementThreads.length; i++) {
            incrementThreads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    lock.writeLock().lock();
                    try {
                        sharedVariable++;
                    } finally {
                        lock.writeLock().unlock();
                    }
                }
            });
            incrementThreads[i].start();
        }

        Thread[] readThreads = new Thread[15];
        for (int i = 0; i < readThreads.length; i++) {
            final int threadID = i;
            readThreads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    lock.readLock().lock();
                    try {
                        long value = sharedVariable;
                        if (j % PRINT_FREQUENCY == 0) {
                            System.out.println("Thread " + threadID + ": " + value);
                        }
                    } finally {
                        lock.readLock().unlock();
                    }
                }
            });
            readThreads[i].start();
        }
        long endTime = System.nanoTime();
        long elapsed = (endTime - startTime) / 1000000; // Convert to milliseconds

        System.out.println("Elapsed time: " + elapsed + " ms");
    }
}
