package spp.exercice1;

public class Foo implements SemaphoreInterface {
    private int count = 0;
    private int waiting = 0;
    public Foo(){}
    public Foo(int nbPermit){
        count=nbPermit;
    }
    @Override
    public synchronized void up() {
        count++;
        notify();
    }

    @Override
    public synchronized void down() throws InterruptedException {
        if(count <= 0){
            waiting++;
            wait();
        }
        count--;
    }

    @Override
    public synchronized int releaseAll() {
        int nbThread = waiting;
        waiting = 0;
        notifyAll();
        return nbThread;
    }
}
