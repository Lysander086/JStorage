package com.example.entity;

import com.example.Config;
import com.example.util.Util;

public class LFUCacheTest {


    public static void main(String[] args) {

        LFUCacheMap map = new LFUCacheMap();

        for (int i = 0; i++ < 5; ) {
            map.put(i, i);
        }
        System.out.println("起始存储情况：\n" + map.showList());//打印起始存储情况


        Util.setTimeout(() -> {
            System.out.println("check still exist 3: " + map.get(3));
        }, Config.DEFAULT_TIMEOUT);

//        System.out.println("命中一个已存在的数据：" + map.showList());//打印命中之后的情况
//
//        map.put(8, 8 + 1);  //存入一个已存在的数据，也就是命中一次缓存中的数据
//        System.out.println("覆盖一个已存在的数据：" + map.showList());//打印命中之后的情况
//
//        map.put(11, 11); //又存入缓存之外的数据
//        System.out.println("新增一个数据后：" + map.showList());//打印又存储一个数据之后的情况



    }

    private void anotherSet(LFUCacheMap map){

        map.put(5, 5); //连续命中两次5
        map.put(5, 5);
        map.put(2, 2); //连续命中三次2
        map.put(2, 2);
        map.put(2, 2);
        System.out.println("修改命中次数后：" + map.showList());//打印又存储一个数据之后的情况
    }

}

//输出结果
//起始存储情况：10=10  9=9  8=8  7=7  6=6  5=5  4=4  3=3  2=2  1=1
//命中一个已存在的数据：10=10  9=9  8=8  6=6  5=5  4=4  3=3  2=2  1=1  7=7
//覆盖一个已存在的数据：10=10  9=9  6=6  5=5  4=4  3=3  2=2  1=1  8=9  7=7
//新增一个数据后：11=11  9=9  6=6  5=5  4=4  3=3  2=2  1=1  8=9  7=7
//修改命中次数后：11=11  9=9  4=4  3=3  2=2  1=1  8=9  7=7  5=5  6=6
