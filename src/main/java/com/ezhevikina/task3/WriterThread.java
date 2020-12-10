package com.ezhevikina.task3;

import java.io.BufferedWriter;
import java.io.IOException;

class WriterThread implements Runnable {
  private final String name;
  private final BufferedWriter bufferedWriter;

  public WriterThread(String name, BufferedWriter bufferedWriter) {
    this.name = name;
    this.bufferedWriter = bufferedWriter;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < 3; i++) {
      synchronized (bufferedWriter) {

        try {
          for (int j = 1; j <= 10; j++) {
            bufferedWriter.write(name + " line " + j + "\n");
          }
        } catch (IOException e) {
          e.printStackTrace();
        }

        try {
          Thread.sleep(20);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }

        bufferedWriter.notifyAll();

        try {
          bufferedWriter.wait(100);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }
}
