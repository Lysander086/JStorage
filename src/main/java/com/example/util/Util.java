package com.example.util;

public class Util {

//    java常用线程方法: https://blog.csdn.net/Javaytp/article/details/79490929

    //  [Asynchronous implementation with JDK 1.8]  https://stackoverflow.com/questions/26311470/what-is-the-equivalent-of-javascript-settimeout-in-java
    public static Thread setTimeout(Runnable runnable, long delay) {
        Thread timer = new Thread(() -> {
            try {
                Thread.sleep(delay);// 如使用了sleep,同步锁的wait,socket中的receiver,accept等方法时, 将处于阻塞状态，
                runnable.run();
            } catch (InterruptedException ie) {
                System.out.println("interrupted " + runnable.toString());
            } catch (Exception e) {
                System.err.println(e);
            }
        });
        timer.start();
        return timer;
    }

    public static void setTimeoutSync(Runnable runnable, int delay) {
        try {
            Thread.sleep(delay);
            runnable.run();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
