package org.example;

public class MultiThreadedCounter extends Thread {

    static long count = 0;

    String threadName ;

    public MultiThreadedCounter(String aName) {
        this.threadName = aName ;
    }

    public void run() {
        for(int i=1; i<=10000000; i++) {
            MultiThreadedCounter.count++ ;
        } // EndFor
    } // EndMethod run



} // EndClass MultiThreadedCounter


