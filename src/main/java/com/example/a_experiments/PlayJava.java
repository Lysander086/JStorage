package com.example.a_experiments;

import com.example.entity.Pair;

import java.util.TreeMap;

public class PlayJava {

    public static void main(String[] args) {
        TreeMap<Long, Pair> tm = new TreeMap();
        long kTime = System.currentTimeMillis();
        tm.put(kTime, new Pair("f", "fly"));

//        Pair res = tm.getEntry(kTime);


    }
}
