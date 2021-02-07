package com.example;

public class Config {
    /*    书写的数字是int类型的，就是说数字的范围在 -2^31 到 2^31 - 1
    * 过长数字使用L
    * */

    public static final long DEFAULT_TIMEOUT = 10 * 1000;

    // 是否完全依赖定时器来对key进行清除
    public static final boolean allDependsOnClearer = false;

}
