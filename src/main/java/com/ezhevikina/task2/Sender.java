package com.ezhevikina.task2;

import java.util.ArrayList;

public class Sender implements Runnable {
  private ArrayList<Package> packages;

  public Sender(ArrayList<Package> packages) {
    this.packages = packages;
  }

  @Override
  public void run() {
    while (!packages.isEmpty()) {

      synchronized (packages) {
        packages.get(0).setSent(true);
        System.out.println(String.format("Package with message %s sent", packages.get(0).getData()));

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
