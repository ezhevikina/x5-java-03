package com.ezhevikina.task3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterMultiThread {

  public static void main(String[] args) {
    FileWriterMultiThread writers = new FileWriterMultiThread();
    writers.write();
  }

  /**
   * Метод, в котором 3 потока построчно пишут данные в файл по 10 записей с периодом в 20 мс
   */

  public void write() {
    File output = new File("./output.txt");

    try (
        FileWriter fileWriter = new FileWriter(output);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    ) {

      Thread[] threads = new Thread[3];

      for (int i = 0; i < 3; i++) {
        threads[i] = new Thread(new WriterThread("Thread" + i, bufferedWriter));
        threads[i].start();
      }

      for (Thread t : threads) {
        t.join();
      }

    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}



