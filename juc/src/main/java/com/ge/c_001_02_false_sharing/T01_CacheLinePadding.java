package com.ge.c_001_02_false_sharing;

import java.util.concurrent.CountDownLatch;

public class T01_CacheLinePadding {
    public static long COUNT = 10_000_000L;

    private static class T {
        /*
         这两行注释的代码，就是为了缓存行对齐，如果打开注释，运行的速度可能会变快。
         为什么说是可能，因为如果没有前后这7个变量，arr[0].x 和 arr[1].x 大概率是在同一个缓存行。

         由于缓存一致性协议的存在。
         T1线程修改arr[0].x 会刷新缓存行，然后会通知T2，缓存行已经失效了，你需要重新从内存读一遍
         T2修改arr[1].x 也会刷新缓存行，也会通知T1，缓存行已经失效，T1也需要从内存中重新load

         正是由于这种机制，当两个线程修改的变量在同一行的时候，会出现互相干扰。

         当在变量x的前后分别增加7个long类型的变量之后，arr[0].x 和 arr[1].x 一定不会处于同一缓存行中。
         所以当两个线程分别修改arr[0].x 和 arr[1].x，连个线程之间不用互相的做通知和同步，所以效率会大大的提升。
         */

        //private long p11, p12, p13, p14, p15, p16, p17;
        public volatile long x = 0L; // 加volatile效果比较明显
        //private long p21, p22, p23, p24, p25, p26, p27;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        // t1循环10亿次，不断的修改arr[0]
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                arr[0].x = i;
            }
            latch.countDown();
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < COUNT; i++) {
                arr[1].x = i;
            }
            latch.countDown();
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        latch.await();
        final long end = System.nanoTime();

        System.out.println((end - start) / 100_0000);
    }
}
