package com.ezhevikina.task1;

/**
 * Создать два потока, первый выводит на экран слово Ping, второй слово Pong.
 * Используйте wait/notify/notifyAll, чтобы вывести последовательность:
 * Ping, Pong, Ping, Pong, Ping, Pong ...
 */

public class PingPong {

  public static void main(String[] args) {
    Object lock = new Object();
    new Thread(new PingPongThread(lock, "Ping")).start();
    new Thread(new PingPongThread(lock, "Pong")).start();
  }
}

class PingPongThread implements Runnable {
  private final Object lock;
  private final String name;

  public PingPongThread(Object lock, String name) {
    this.lock = lock;
    this.name = name;
  }

  @Override
  public void run() {
    synchronized (lock) {
      while (true) {
        System.out.println(name);

        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        lock.notify();

        try {
          lock.wait(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
