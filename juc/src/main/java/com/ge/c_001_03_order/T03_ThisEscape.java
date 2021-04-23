package com.ge.c_001_03_order;

import java.io.IOException;

public class T03_ThisEscape {
  private int num = 8;

  public T03_ThisEscape() {
    // 这里有可能输出的是中间状态值0，而不是8
    new Thread(() -> System.out.println(this.num)).start();
  }

  public static void main(String[] args) throws IOException {
    new T03_ThisEscape();
    System.in.read();
  }
}
