package org.example;

public class MultiThreadPrinter extends Thread {
    String threadName;

    public MultiThreadPrinter(String aName) {
        this.threadName = aName ;
    }

    public void run() {
        if (MultiThreadedCounter.count % 20000 == 0) {
            System.err.println(this.threadName + " count = " + MultiThreadedCounter.count);

        }
    }
}
