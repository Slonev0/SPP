public class MultiThreadedCounter extends Thread {

  static long count = 0;

  String threadName ;

  public MultiThreadedCounter(String aName) {
    this.threadName = aName ;
  }

  public void run() {
    for(int i=1; i<=100000; i++) {
      MultiThreadedCounter.count++ ;
    } // EndFor
  } // EndMethod run
  public static long getCount() {
    return count;
  }
  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new MultiThreadedCounter("t1");
    Thread t2 = new MultiThreadedCounter("t2");
    Thread t3 = new MultiThreadedCounter("t3");
    Thread t4 = new MultiThreadedCounter("t4");
    Thread t5 = new MultiThreadedCounter("t5");
    Thread t6 = new MultiThreadedPrinter("t6", count);
    Thread t7 = new MultiThreadedPrinter("t7", count);
    Thread t8 = new MultiThreadedPrinter("t8", count);
    Thread t9 = new MultiThreadedPrinter("t9", count);
    Thread t10 = new MultiThreadedPrinter("t10", count);
    Thread t11 = new MultiThreadedPrinter("t11", count);
    Thread t12 = new MultiThreadedPrinter("t12", count);
    Thread t13 = new MultiThreadedPrinter("t13", count);
    Thread t14 = new MultiThreadedPrinter("t14", count);
    Thread t15 = new MultiThreadedPrinter("t15", count);
    Thread t16 = new MultiThreadedPrinter("t16", count);
    Thread t17 = new MultiThreadedPrinter("t17", count);
    Thread t18 = new MultiThreadedPrinter("t18", count);
    Thread t19 = new MultiThreadedPrinter("t19", count);
    Thread t20 = new MultiThreadedPrinter("t20", count);
    t1.start();
    t2.start();
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
    t3.start();
    t4.start();
    t5.start();
  }
} // EndClass MultiThreadedCounter



