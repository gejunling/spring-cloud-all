package com.ge.c_000_threadbasic;

import com.ge.SleepHelper;

import java.util.concurrent.locks.ReentrantLock;

public class T11_Interrupt_and_lockInterruptibly {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                SleepHelper.seconds(10);
            } finally {
                lock.unlock();
            }
            System.out.println("t1 end!");
        });

        t1.start();

        SleepHelper.seconds(1);

        Thread t2 = new Thread(() -> {
            System.out.println("t2 start!");
            try {
                // 这个方法上锁，是可以被打断的，然后catch之后该怎么处理，就看程序员想怎么处理了。
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("t2 end!");
        });
        t2.start();
        SleepHelper.seconds(1);
        t2.interrupt();
    }
}
