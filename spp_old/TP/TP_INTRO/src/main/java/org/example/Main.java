package org.example;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new MultiThreadedCounter("t1");
        Thread t2 = new MultiThreadedCounter("t2");
        Thread t3 = new MultiThreadedCounter("t3");
        Thread t4 = new MultiThreadedCounter("t4");
        Thread t5 = new MultiThreadedCounter("t5");
        Thread t6 = new MultiThreadPrinter("t6");
        Thread t7 = new MultiThreadPrinter("t7");
        Thread t8 = new MultiThreadPrinter("t8");
        Thread t9 = new MultiThreadPrinter("t9");
        Thread t10 = new MultiThreadPrinter("t10");
        Thread t11 = new MultiThreadPrinter("t11");
        Thread t12 = new MultiThreadPrinter("t12");
        Thread t13 = new MultiThreadPrinter("t13");
        Thread t14 = new MultiThreadPrinter("t14");
        Thread t15 = new MultiThreadPrinter("t15");
        Thread t16 = new MultiThreadPrinter("t16");
        Thread t17 = new MultiThreadPrinter("t17");
        Thread t18 = new MultiThreadPrinter("t18");
        Thread t19 = new MultiThreadPrinter("t19");
        Thread t20 = new MultiThreadPrinter("t20");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();
        t13.start();
        t14.start();
        t15.start();
        t16.start();
        t17.start();
        t18.start();
        t19.start();
        t20.start();
    }
}
