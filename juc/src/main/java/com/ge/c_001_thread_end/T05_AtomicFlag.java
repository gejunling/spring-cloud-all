package com.ge.c_001_thread_end;

import com.ge.SleepHelper;

import java.util.concurrent.atomic.AtomicBoolean;

public class T05_AtomicFlag {
    private static AtomicBoolean running = new AtomicBoolean(true);

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (running.get()) {

            }
            System.out.println("t1 end;");
        });
        t.start();
        SleepHelper.seconds(1);
        running.set(false);
    }
}
