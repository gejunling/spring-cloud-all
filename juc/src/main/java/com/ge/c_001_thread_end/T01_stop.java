package com.ge.c_001_thread_end;

import com.ge.SleepHelper;

public class T01_stop {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("go on");
                SleepHelper.seconds(1);
            }
        });
        t.start();
        SleepHelper.seconds(5);
        t.stop();
    }
}
