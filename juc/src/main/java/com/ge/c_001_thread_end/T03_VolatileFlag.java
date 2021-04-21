package com.ge.c_001_thread_end;

import com.ge.SleepHelper;

public class T03_VolatileFlag {
    private static volatile boolean running = true;

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            long i = 0L;
            while (running) {
                i++;
            }
            System.out.println("end and i = " + i);
        });

        t.start();
        SleepHelper.seconds(1);
        running = false;
    }
}
