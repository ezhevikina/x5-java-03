package com.ezhevikina.task4;

public class Printer extends MultiFunctionalUnit implements Runnable {
  private Document document;
  private Object printLock;

  public Printer(Document document, Object printLock) {
    this.document = document;
    this.printLock = printLock;
  }

  @Override
  public void doStuffWithDocument(Document document) throws InterruptedException {
    for (int i = 1; i <= document.getPages(); i++) {
      System.out.println(String.format("Document %s. Page %d of %d scanned",
          document.getName(), i, document.getPages()));
      Thread.sleep(50);
    }
  }

  @Override
  public void run() {

    synchronized (printLock) {
      try {
          doStuffWithDocument(document);
          Thread.sleep(1000);
          printLock.notify();
          System.out.println();
          printLock.wait(1000);

      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}
