package com.ezhevikina.task1;

public class PingPongPlayer implements Runnable {
  private final Integer armFallsOff;
  private final String name;
  private int hitsMade = 0;

  public PingPongPlayer(Integer playerArmFallsOff, String name) {
    this.armFallsOff = playerArmFallsOff;
    this.name = name;
  }

  @Override
  public void run() {
    synchronized (armFallsOff) {
      while (hitsMade < armFallsOff) {
        System.out.println(name);
        hitsMade++;

        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }

        armFallsOff.notify();

        try {
          armFallsOff.wait(1000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
      System.out.println(String.format("%s's arm fell off", name));
    }
  }
}
