package com.ezhevikina.task2;

import java.util.Timer;
import java.util.TimerTask;

public class TimeOutTask extends TimerTask {
  private Thread[] t;
  private Timer timer;

  TimeOutTask(Timer timer, Thread... t) {
    this.t = t;
    this.timer = timer;
  }

  public void run() {
    for (Thread thread : t) {
      if (thread != null && thread.isAlive()) {
        thread.interrupt();
        timer.cancel();
      }
    }
  }
}