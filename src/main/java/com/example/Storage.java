package com.example;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class Storage {

    private Map<String, Node> storageMap = new ConcurrentHashMap<>();

    private TreeMap<String, String> treeForCleaner = new TreeMap<>();


    public String get(String key) throws NullPointerException {
        if (key == null) {
            return null;
        }

        // todo 根据timeOut再次清除 timerTree 和 storageMap
        return this.storageMap.get(key).val;
    }

    public void put(String key, String val, long timeOut) {

        this.storageMap.put(key, new Node(key, val, timeOut, (new Date())));
    }

    private class Node implements Comparable{
        String key;
        String val;
        long timeOut;
        // lastUpdatedTime + timeOut > now() ? "canGet" : "noGet and make it invalid"
        Date lastUpdatedTime;

        public Node(String key, String val, long timeOut, Date lastUpdatedTime) {
            this.key = key;
            this.val = val;
            this.timeOut = timeOut;
            this.lastUpdatedTime = lastUpdatedTime;
        }


        // sort via timeOut, used in timerTree
        @Override
        public int compareTo(Object o) {
            if (!(o instanceof Node)){
                return 1;
            }
            // todo
            return -1;

        }
    }


}
