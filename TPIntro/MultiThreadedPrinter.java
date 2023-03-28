public class MultiThreadedPrinter extends Thread {
    String threadName;

    public MultiThreadedPrinter(String threadName, long count) {
        this.threadName = threadName;
    }

    public void run(){
        for(int i=1; i<=100000; i++) {
            if (i % 20000 == 0){
            System.err.println(this.threadName+ " count = " + MultiThreadedCounter.getCount());
            } // EndIf
        } // EndFor
    }
}