package com.ezhevikina.task4;

public class Document {
  private String name;
  private int pages;

  public Document(int pages, String name) {
    this.pages = pages;
    this.name = name;
  }

  public int getPages() {
    return pages;
  }

  public String getName() {
    return name;
  }
}
