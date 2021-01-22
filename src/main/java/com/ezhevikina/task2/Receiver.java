package com.ezhevikina.task2;

import java.util.ArrayList;

public class Receiver implements Runnable {
  private ArrayList<Package> packages;

  public Receiver(ArrayList<Package> packages) {
    this.packages = packages;
  }

  @Override
  public void run() {
    while (!packages.isEmpty()) {

      synchronized (packages) {
        Package pack = packages.get(0);

        if (pack.isSent()) {
          pack.setReceived(true);
          System.out.println(String.format("Package with message %s received", packages.get(0).getData()));
          packages.remove(0);
        }

        try {
          Thread.sleep(500);
          packages.notify();
          packages.wait(1000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }
}
