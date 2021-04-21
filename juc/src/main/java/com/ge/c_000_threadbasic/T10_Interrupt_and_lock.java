package com.ge.c_000_threadbasic;

import com.ge.SleepHelper;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class T10_Interrupt_and_lock {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                // 第一个线程持有锁10秒钟
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("t1 end!");
        });

        t1.start();

        SleepHelper.seconds(1);

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
            } finally {
                lock.unlock();
            }
            System.out.println("t2 end!");
        });
        // 1秒之后启动t2
        t2.start();
        SleepHelper.seconds(1);
        t2.interrupt(); // 再过一秒，打断线程
    }
}
