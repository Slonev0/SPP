package spp.exercice2;

import java.util.Random;
import java.util.concurrent.Exchanger;

import static java.lang.Thread.sleep;

public class Main {
    static Exchanger<String> exchangeur = new Exchanger<>();

    public static void main(String[] args) {
        Thread alice = new Thread(new Alice());
        Thread bob = new Thread(new Bob());
        alice.start();
        bob.start();
    }
    public static class Bob implements Runnable {
        private String name = "Bob";
        private String value = "Pong";
        private int iteration = 0;

        public void run() {
            for (int i = 0; i<=2; i++) {
                System.out.println("Iteration: " + iteration + " " + name + " has " + value);
                System.out.println("Iteration: " + iteration + " " + name + " going to sleep");
                Random r = new Random();
                try {
                    sleep(r.nextInt(5000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Iteration: " + iteration + " " + name + " ready to exchange");
                try {
                    value = exchangeur.exchange(value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                iteration++;
            }
        }

    }

    public static class Alice implements Runnable {
        private String name = "Alice";
        private String value = "Ping";
        private int iteration = 0;


        public void run() {
            for (int i = 0; i <= 2; i++) {
                System.out.println("Iteration: " + iteration + " " + name + " has " + value);
                System.out.println("Iteration: " + iteration + " " + name + " going to sleep");
                Random r = new Random();
                try {
                    sleep(r.nextInt(5000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Iteration: " + iteration + " " + name + " ready to exchange");
                try {
                    value = exchangeur.exchange(value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                iteration++;
            }
        }

    }

}



