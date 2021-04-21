package com.ge.c_001_thread_end;

import com.ge.SleepHelper;

import java.time.Instant;

public class T02_Suspend_Resume {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("go on -- " + Instant.now().toEpochMilli());
                SleepHelper.seconds(1);
            }
        });
        t.start();

        SleepHelper.seconds(5);
        System.out.println("暂停线程 ----------");
        t.suspend();
        SleepHelper.seconds(3);
        System.out.println("恢复线程 ---------");
        t.resume();
    }
}
