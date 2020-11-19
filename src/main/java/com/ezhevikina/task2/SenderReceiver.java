package com.ezhevikina.task2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;

/**
 * Реализовать приложение Sender-Receiver, которое будет использовать методы
 * wait () и notify () для настройки синхронизации между ними:
 * Отправитель должен отправить пакет данных Получателю.
 * Получатель не может обработать пакет данных, пока Отправитель не закончит его отправку.
 * Точно так же Отправитель не должен пытаться отправить другой пакет, если
 * Получатель уже не обработал предыдущий пакет.
 */

public class SenderReceiver {

  public static void main(String[] args) {
    ArrayList<String> envelope = new ArrayList<>();
    Thread sender = new Thread(new SenderThread(envelope));
    Thread receiver = new Thread(new ReceiverThread(envelope));
    Timer timer = new Timer();
    TimeOutTask taskSend = new TimeOutTask(timer, sender, receiver);
    sender.start();
    receiver.start();
    timer.schedule(taskSend, 5000);
  }
}

class SenderThread implements Runnable {
  private final ArrayList<String> envelopeToSend;

  public SenderThread(ArrayList<String> envelope) {
    this.envelopeToSend = envelope;
  }

  @Override
  public void run() {
    synchronized (envelopeToSend) {
      while (!Thread.currentThread().isInterrupted()) {
        System.out.println("Sender started to send an envelope");

        for (int i = 1; i <= 5; i++) {
          System.out.println("part " + i);
          envelopeToSend.add("part " + i);
        }

        System.out.println("Envelope sent");

        try {
          Thread.sleep(1000);
          envelopeToSend.notify();
          envelopeToSend.wait();
        } catch (InterruptedException e) {

          Thread.currentThread().interrupt();
        }
      }
    }
  }
}

class ReceiverThread implements Runnable {
  private final ArrayList<String> envelopeToReceive;

  public ReceiverThread(ArrayList<String> envelope) {
    this.envelopeToReceive = envelope;
  }

  @Override
  public void run() {
    synchronized (envelopeToReceive) {
      while (!Thread.currentThread().isInterrupted()) {

        if (!envelopeToReceive.isEmpty()) {
          System.out.println("Receiver started to receive an envelope");
          Iterator iterator = envelopeToReceive.iterator();

          while (iterator.hasNext()) {
            System.out.println(envelopeToReceive.get(0));
            envelopeToReceive.remove(0);
          }

          System.out.println("Envelope received");

          try {
            Thread.sleep(1000);
            envelopeToReceive.notify();
            envelopeToReceive.wait();
          } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
          }
        }
      }
    }
  }
}
