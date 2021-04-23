package com.ge.c_001_sync_basic;

import java.util.concurrent.CountDownLatch;

public class T00_00_IPlusPlus {
  private static long n = 0L;

  public static void main(String[] args) throws InterruptedException {
    // 一共有100个线程
    Thread[] threads = new Thread[100];

    CountDownLatch latch = new CountDownLatch(threads.length);

    for (int i = 0; i < threads.length; i++) {
      threads[i] =
          new Thread(
              () -> {
                // 每个线程内部对n做1万次 n++
                for (int j = 0; j < 10000; j++) {
                  synchronized (T00_00_IPlusPlus.class) {
                    n++;
                  }
                }
                latch.countDown();
              });
    }

    for (Thread thread : threads) {
      thread.start();
    }
    latch.await();
    // 正常情况，n 应该 = 100W，但实际上并不是
    System.out.println(n);
  }
}
