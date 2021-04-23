package com.ge.c_001_03_order;

public class T02_NoVisibility {
  private static /*volatile*/ boolean ready = false;
  private static int number;

  private static class ReaderThread extends Thread {
    @Override
    public void run() {
      while (!ready) {
        Thread.yield();
      }
      System.out.println(number);
    }
  }
  /*
  问题1：ready=true之后，线程不会马上停止，所以ready应该用volatile修饰
  问题2：有序性问题，设置ReaderThread中最终打印的结果=0
        因为 number=42 和 ready = true 之间并没有依赖关系
        如果这两行代码换顺序了，输出就=0
   */

  public static void main(String[] args) throws InterruptedException {
    Thread t = new ReaderThread();
    t.start();
    number = 42;
    ready = true;
    t.join();
  }
}
