package com.ezhevikina.task2;

import java.util.ArrayList;

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
    ArrayList<Package> packages = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      packages.add(new Package("message" + i));
    }

    Thread sender = new Thread(new Sender(packages));
    Thread receiver = new Thread(new Receiver(packages));
    sender.start();
    receiver.start();
  }
}
