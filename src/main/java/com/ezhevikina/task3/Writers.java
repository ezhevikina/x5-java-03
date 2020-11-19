package com.ezhevikina.task3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writers {

  public static void main(String[] args) {
    Writers writers = new Writers();
    writers.write();
  }

  /**
   * Метод, в котором 3 потока построчно пишут данные в файл по 10 записей с периодом в 20 мс
   */

  public void write() {
    File output = new File("./output.txt");
    FileWriter fileWriter = null;
    BufferedWriter bufferedWriter = null;

    try {
      fileWriter = new FileWriter(output);
      bufferedWriter = new BufferedWriter(fileWriter);

      Thread t1 = new Thread(new WriterThread("Thread 1", bufferedWriter));
      Thread t2 = new Thread(new WriterThread("Thread 2", bufferedWriter));
      Thread t3 = new Thread(new WriterThread("Thread 3", bufferedWriter));

      t1.start();
      t2.start();
      t3.start();
      t1.join();
      t2.join();
      t3.join();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    } finally {

      try {
        bufferedWriter.close();
        fileWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

class WriterThread implements Runnable {
  private final String name;
  private final BufferedWriter bufferedWriter;

  public WriterThread(String name, BufferedWriter bufferedWriter) {
    this.name = name;
    this.bufferedWriter = bufferedWriter;
  }

  @Override
  public void run() {
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
          e.printStackTrace();
        }

        bufferedWriter.notifyAll();

        try {
          bufferedWriter.wait(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}



