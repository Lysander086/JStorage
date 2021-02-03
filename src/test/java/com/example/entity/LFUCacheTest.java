package com.example.entity;

import com.example.Config;
import com.example.LFUCache;
import com.example.util.Util;

public class LFUCacheTest {


    public static void main(String[] args) {

        LFUCache map = new LFUCache();

        for (int i = 0; i++ < 5; ) {
            map.put(i, i);
        }
        System.out.println("initial  storage：\n" + map.showList());


        Util.setTimeout(() -> {
            System.out.println("check still exist 3: " + map.get(3));
        }, Config.DEFAULT_TIMEOUT);


    }

    private void anotherSet(LFUCache map) {

//        map.put(5, 5); //连续命中两次5
//        map.put(5, 5);
//        map.put(2, 2); //连续命中三次2
//        map.put(2, 2);
//        map.put(2, 2);
        System.out.println("修改命中次数后：" + map.showList());//打印又存储一个数据之后的情况
    }

}

//输出结果
//起始存储情况：10=10  9=9  8=8  7=7  6=6  5=5  4=4  3=3  2=2  1=1
//命中一个已存在的数据：10=10  9=9  8=8  6=6  5=5  4=4  3=3  2=2  1=1  7=7
//覆盖一个已存在的数据：10=10  9=9  6=6  5=5  4=4  3=3  2=2  1=1  8=9  7=7
//新增一个数据后：11=11  9=9  6=6  5=5  4=4  3=3  2=2  1=1  8=9  7=7
//修改命中次数后：11=11  9=9  4=4  3=3  2=2  1=1  8=9  7=7  5=5  6=6
