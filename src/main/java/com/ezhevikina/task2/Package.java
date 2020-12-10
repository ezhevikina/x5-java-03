package com.ezhevikina.task2;

public class Package {
  private final String data;
  private boolean isSent = false;
  private boolean isReceived = false;

  public Package(String data) {
    this.data = data;
  }

  public String getData() {
    return data;
  }

  public boolean isSent() {
    return isSent;
  }

  public void setSent(boolean sent) {
    isSent = sent;
  }

  public void setReceived(boolean received) {
    isReceived = received;
  }
}
