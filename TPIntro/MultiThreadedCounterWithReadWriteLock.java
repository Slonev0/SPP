import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultiThreadedCounterWithReadWriteLock extends Thread {

    static long count = 0;
    static ReadWriteLock myLock = new ReentrantReadWriteLock();

    String threadName ;

    public MultiThreadedCounterWithReadWriteLock(String aName) {
        this.threadName = aName ;
    }

    public void run() {
        long startTime = System.nanoTime();
        for(int i=1; i<=1000; i++) {
            myLock.writeLock().lock();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            MultiThreadedCounterWithReadWriteLock.count++ ;
            myLock.writeLock().unlock();
            if (i%200==0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.err.println(this.threadName + ": i = " + i + " count = " + count);
            }
        } // EndFor
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime);
        System.out.println("Le temps d'exécution est de " + elapsedTime + " nanosecondes.");
    } // EndMethod run

    public static void main(String[] args) {
        Thread t1 = new MultiThreadedCounterWithReadWriteLock("t1");
        Thread t2 = new MultiThreadedCounterWithReadWriteLock("t2");
        long startTime = System.nanoTime();
        t1.start();
        t2.start();
    }

} // EndClass MultiThreadedCounterWithReadWriteLock

