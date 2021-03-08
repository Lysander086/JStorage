package com.example.entity;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * -
 *
 * @param <K>
 * @param <V>
 */
public class MyTreeMap<K, V> extends TreeMap {


    private final Comparator<? super K> comparator;

    public MyTreeMap(Comparator<? super K> comparator) {this.comparator = comparator;}

    public MyTreeMap() {
        comparator = null;
    }




}
