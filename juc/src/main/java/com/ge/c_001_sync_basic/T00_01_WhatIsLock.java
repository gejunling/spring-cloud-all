package com.ge.c_001_sync_basic;

import com.ge.SleepHelper;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

public class T00_01_WhatIsLock {
  private static Object o = new Object();

  public static void main(String[] args) throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(3);
    Runnable r =
        () -> {
          // synchronized (o) {
          System.out.println(Thread.currentThread().getName() + " start!");
          SleepHelper.seconds(2);
          System.out.println(Thread.currentThread().getName() + " end!");
          // }
          latch.countDown();
        };
    long start = Instant.now().getEpochSecond();
    for (int i = 0; i < 3; i++) {
      new Thread(r).start();
    }
    latch.await();
    long end = Instant.now().getEpochSecond();

    System.out.println("cost " + (end - start) + " 秒");
  }
}
