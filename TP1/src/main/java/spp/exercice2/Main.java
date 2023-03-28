package spp.exercice2;

import java.util.Random;
import java.util.concurrent.Exchanger;

import static java.lang.Thread.sleep;

public class Main {
    /**
     * Exchanger permettant d'échanger des chaînes de caractères entre deux threads
     */
    static Exchanger<String> exchangeur = new Exchanger<>();

    /**
     * Programme principal, on crée deux threads Alice et Bob et on les lance
     * @param args
     */
    public static void main(String[] args) {
        Thread alice = new Thread(new Alice());
        Thread bob = new Thread(new Bob());
        alice.start();
        bob.start();
    }

    /**
     * Classe Bob, qui va échanger des chaînes de caractères avec Alice
     * Bob commence avec la valeur "Pong"
     */
    public static class Bob implements Runnable {
        private String name = "Bob";
        private String value = "Pong";
        private int iteration = 0;

        /**
         * Méthode run du thread Bob
         */
        public void run() {
            for (int i = 0; i<=2; i++) {
                System.out.println("Iteration: " + iteration + " " + name + " has " + value);
                System.out.println("Iteration: " + iteration + " " + name + " going to sleep");
                // On fait dormir le thread entre 0 et 5 secondes
                Random r = new Random();
                try {
                    sleep(r.nextInt(5000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Iteration: " + iteration + " " + name + " ready to exchange");

                // On va échanger la valeur avec Alice grâce à l'Exchanger
                try {
                    value = exchangeur.exchange(value);
                    System.out.println("Iteration: " + iteration + " " + name + " exchange completed ");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // On augmente le nombre d'itérations
                iteration++;
            }
        }

    }
    /**
     * Classe Alice, qui va échanger des chaînes de caractères avec Bob
     * Bob commence avec la valeur "Ping"
     */
    public static class Alice implements Runnable {
        private String name = "Alice";
        private String value = "Ping";
        private int iteration = 0;

        /**
         * Méthode run du thread Alice
         */
        public void run() {
            for (int i = 0; i <= 2; i++) {
                System.out.println("Iteration: " + iteration + " " + name + " has " + value);
                System.out.println("Iteration: " + iteration + " " + name + " going to sleep");
                // On fait dormir le thread entre 0 et 5 secondes
                Random r = new Random();
                try {
                    sleep(r.nextInt(5000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Iteration: " + iteration + " " + name + " ready to exchange");

                // On va échanger la valeur avec Alice grâce à l'Exchanger
                try {
                    value = exchangeur.exchange(value);
                    System.out.println("Iteration: " + iteration + " " + name + " exchange completed ");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // On augmente le nombre d'itérations
                iteration++;
            }
        }

    }

}



