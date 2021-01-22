package com.ezhevikina.task1;

/**
 * Создать два потока, первый выводит на экран слово Ping, второй слово Pong.
 * Используйте wait/notify/notifyAll, чтобы вывести последовательность:
 * Ping, Pong, Ping, Pong, Ping, Pong ...
 */

public class PingPongGame {
  public static void main(String[] args) {
    Integer playerArmFallsOff = 10;
    new Thread(new PingPongPlayer(playerArmFallsOff, "Ping")).start();
    new Thread(new PingPongPlayer(playerArmFallsOff, "Pong")).start();
  }
}

