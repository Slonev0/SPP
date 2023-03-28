/**
 * @author Francois Taiani   <f.taiani@lancaster.ac.uk>
 */

import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadedCounterWithLock extends Thread {

  static long count = 0;
  static ReentrantLock myLock = new ReentrantLock();

  String threadName ;

  public MultiThreadedCounterWithLock(String aName) {
    this.threadName = aName ;
  }

  public void run() {
    long startTime = System.nanoTime();
    for(int i=1; i<=100000; i++) {
      myLock.lock();
      MultiThreadedCounterWithLock.count++ ;
      myLock.unlock();
      if (i%20000==0)
        System.err.println(this.threadName+ ": i = " + i + " count = " + count);
    } // EndFor
    long endTime = System.nanoTime();
    long elapsedTime = (endTime - startTime);
    System.out.println("Le temps d'exécution est de " + elapsedTime + " nanosecondes.");
  } // EndMethod run

  public static void main(String[] args) {
    Thread t1 = new MultiThreadedCounterWithLock("t1");
    Thread t2 = new MultiThreadedCounterWithLock("t2");
    t1.start();
    t2.start();
  }

} // EndClass MultiThreadedCounterWithLock
