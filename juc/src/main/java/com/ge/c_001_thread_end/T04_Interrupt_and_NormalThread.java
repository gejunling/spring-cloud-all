package com.ge.c_001_thread_end;

import com.ge.SleepHelper;

public class T04_Interrupt_and_NormalThread {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (!Thread.interrupted()) {
                //sleep wait
            }
            System.out.println("t1 end!");
        });

        t.start();
        SleepHelper.seconds(1);
        t.interrupt();
    }
}
