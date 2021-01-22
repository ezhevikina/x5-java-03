package com.ezhevikina.task4;

public class Scanner extends MultiFunctionalUnit implements Runnable {
  private Document document;
  private Object scanLock;

  public Scanner(Document document, Object scanLock) {
    this.document = document;
    this.scanLock = scanLock;
  }

  @Override
  public void doStuffWithDocument(Document document) throws InterruptedException {
    for (int i = 1; i <= document.getPages(); i++) {
      System.out.println(String.format("Document %s. Page %d of %d printed",
          document.getName(), i, document.getPages()));
      Thread.sleep(50);
    }
  }

  @Override
  public void run() {
    synchronized (scanLock) {
      try {
          doStuffWithDocument(document);
          Thread.sleep(1000);
          scanLock.notify();
          System.out.println();
          scanLock.wait(1000);

      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}
