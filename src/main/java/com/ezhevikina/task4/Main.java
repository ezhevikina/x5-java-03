package com.ezhevikina.task4;

/**
 * Написать класс МФУ, на котором возможно одновременно выполнять печать
 * и сканирование документов, но нельзя одновременно печатать или сканировать
 * два документа. При печати в консоль выводится сообщения «Отпечатано 1, 2, 3,... страницы»,
 * при сканировании – аналогично «Отсканировано...». Вывод в консоль с периодом в 50 мс
 */

public class Main {

  public static void main(String[] args) {

    Object printLock = new Object();
    Object scanLock = new Object();

    for (int i = 1; i <= 5; i++) {
      Document document = new Document(i, "doc" + i);
      Thread pThread = new Thread(new Printer(document, printLock));
      Thread sThread = new Thread(new Scanner(document, scanLock));
      pThread.start();
      sThread.start();
    }
  }
}

