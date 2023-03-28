package org.example; /**
 * @author Francois Taiani   <f.taiani@lancaster.ac.uk>
 */

import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadedCounterWithLockSleep extends Thread {

  static long count = 0;
  static ReentrantLock myLock = new ReentrantLock();

  String threadName ;

  public MultiThreadedCounterWithLockSleep(String aName) {
    this.threadName = aName ;
  }

  public void run() {
    for(int i=1; i<=1000; i++) {
      myLock.lock();
      MultiThreadedCounterWithLockSleep.count++ ;
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      myLock.unlock();
      if (i%200==0)
        System.err.println(this.threadName+ ": i = " + i + " count = " + count);
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    } // EndFor
  } // EndMethod run

  public static void main(String[] args) {
    Thread t1 = new MultiThreadedCounterWithLockSleep("t1");
    Thread t2 = new MultiThreadedCounterWithLockSleep("t2");
    long startTime = System.nanoTime();
    t1.start();
    t2.start();
    long endTime = System.nanoTime();
    System.out.println("Temps mis au programme : "+ (endTime-startTime)+" nanoSec");
  }

} // EndClass MultiThreadedCounterWithLock
