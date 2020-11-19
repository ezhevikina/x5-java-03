package com.ezhevikina.task4;

/**
 * Написать класс МФУ, на котором возможно одновременно выполнять печать
 * и сканирование документов, но нельзя одновременно печатать или сканировать
 * два документа. При печати в консоль выводится сообщения «Отпечатано 1, 2, 3,... страницы»,
 * при сканировании – аналогично «Отсканировано...». Вывод в консоль с периодом в 50 мс
 */

public class PrinterScanner {

  public static void main(String[] args) {
    Object printLock = new Object();
    Object scanLock = new Object();

    for (int i = 0; i < 5; i++) {
      new Thread(new ScannerThread(scanLock)).start();
      new Thread(new PrinterThread(printLock)).start();
    }
  }
}

class ScannerThread implements Runnable {
  private final Object scanLock;

  public ScannerThread(Object scanLock) {
    this.scanLock = scanLock;
  }

  @Override
  public void run() {
    synchronized (scanLock) {
      if (Thread.holdsLock(scanLock)) {

        for (int i = 1; i <= 5; i++) {
          System.out.println(Thread.currentThread().getName() + ":" + i + " page(s) scanned");

          try {
            Thread.sleep(50);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

        System.out.println();

        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        scanLock.notify();
      } else {

        try {
          scanLock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}

class PrinterThread implements Runnable {
  private final Object printLock;

  public PrinterThread(Object printLock) {
    this.printLock = printLock;
  }

  @Override
  public void run() {
    synchronized (printLock) {
      if (Thread.holdsLock(printLock)) {

        for (int i = 1; i <= 5; i++) {
          System.out.println(Thread.currentThread().getName() + ":" + i + " page(s) printed");

          try {
            Thread.sleep(50);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

        System.out.println();

        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        printLock.notify();
      } else {

        try {
          printLock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
