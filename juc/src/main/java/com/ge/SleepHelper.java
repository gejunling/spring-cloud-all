package com.ge;

import java.util.concurrent.TimeUnit;

public class SleepHelper {

    public static void seconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void milliSeconds(int seconds) {
        try {
            TimeUnit.MICROSECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
