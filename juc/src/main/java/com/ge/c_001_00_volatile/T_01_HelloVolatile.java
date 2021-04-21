package com.ge.c_001_00_volatile;

import com.ge.SleepHelper;

public class T_01_HelloVolatile {
    private static /*volatile*/ boolean running = true;

    private static void m() {
        System.out.println(" m start");
        while (running) {

        }
        System.out.println("m end!");

    }

    public static void main(String[] args) {
        new Thread(T_01_HelloVolatile::m, "t1").start();
        SleepHelper.seconds(1);
        running = false;
    }
}
