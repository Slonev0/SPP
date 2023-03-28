package spp.exercice1;

/**
 * Classe Foo, implémentation de SemaphoreInterface
 */
public class Foo implements SemaphoreInterface {
    /**
     * Nombre de jetons disponibles
     */
    private int count = 0;

    /**
     * Nombre de threads en attente
     */
    private int waiting = 0;

    /**
     * Constructeur de Foo par défaut, demandé dans le sujet
     */
    public Foo(){}

    /**
     * Constructeur de Foo avec un nombre de jetons initial
     * @param nbPermit Nombre de jetons initial
     */
    public Foo(int nbPermit){
        count=nbPermit;
    }

    /**
     * Méthode up, ajoute un jeton et réveille un thread en attente
     */
    @Override
    public synchronized void up() {
        count++;
        notify();
    }

    /**
     * Méthode down, retire un jeton, met le thread en attente s'il n'y a plus de jeton
     * @throws InterruptedException
     */
    @Override
    public synchronized void down() throws InterruptedException {
        if(count <= 0){
            waiting++;
            wait();
        }
        count--;
    }

    /**
     * Méthode releaseAll, réveille tous les threads en attente
     * @return Nombre de threads réveillés
     */
    @Override
    public synchronized int releaseAll() {
        int nbThread = waiting;
        waiting = 0;
        notifyAll();
        return nbThread;
    }
}
