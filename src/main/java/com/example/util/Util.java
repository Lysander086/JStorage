package com.example.util;

import java.util.Random;

public class Util {

//    java常用线程方法: https://blog.csdn.net/Javaytp/article/details/79490929

    public static Thread setTimeout(Runnable runnable, long delay) {
        Thread timer = new Thread(() -> {
            try {
                Thread.sleep(delay);// 如使用了sleep,同步锁的wait,socket中的receiver,accept等方法时, 将处于阻塞状态，
                runnable.run();
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

    public static String[] generateRandomWords(int numberOfWords)
    {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }
}
