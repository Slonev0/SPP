package spp.exercice1;

public interface SemaphoreInterface {
  public void up();
  public void down() throws InterruptedException;
  public int  releaseAll();
} // EndInterface SemaphoreInterface
