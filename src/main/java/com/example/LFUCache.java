package com.example;

import com.example.util.Util;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 最不常使用 (Least Frequently Used)
 * - 最不常使用的排在最后面 ( 根据引用次数排序 )
 */
@Service
@NoArgsConstructor
public class LFUCache {

    @Getter
    private static Map<Object, Value> cacheMap = new ConcurrentHashMap<>();
    @Setter
    @Getter
    private int cacheSize = 0;


    //获取缓存中的数据
    public Object get(Object key) throws NullPointerException {
        if (key == null)
            return null;
        //命中+1
        cacheMap.get(key).countIncrement();
        return cacheMap.get(key).val;
    }


    /**
     * with DEFAULT_TIMEOUT
     *
     * @param key
     * @param val
     */
    public void put(Object key, Object val) {
        this.put(key, val, Config.DEFAULT_TIMEOUT);
    }

    /**
     * 存储数据
     *
     * @param key
     * @param val
     * @param timeOut
     */
    public void put(Object key, Object val, long timeOut) {

        //如果本来就存在
        if (cacheMap.get(key) != null) {
            this.cacheSize++;
            cacheMap.get(key).renewTimeOutTask(key, timeOut);
            cacheMap.get(key).countIncrement();
            cacheMap.get(key).setVal(val);
        } else {

            Value value = new Value(val, key, timeOut);
            value.createTimeoutTask(key);
            cacheMap.put(key, value);
        }
        //
    }

    // unused: 数据移除最后一个数据
    public void remove() {
        //先拿到最后一个数据
        Value v = Collections.min(cacheMap.values());
        //移除最后一个数据
        cacheMap.remove(v.key);
    }

    //获取存储情况
    public String showList() {
        List<Value> list = new ArrayList<>(cacheMap.values());

        Collections.sort(list);

        StringBuilder stb = new StringBuilder();
        for (Value value : list) {
            stb.append(value.key).append(": ").append(value.val).append("\n");
        }
        return stb.toString();
    }

    static class Value implements Comparable<Value> {    //定义一个静态内部类，主要是用于统计命中数
        Object key;
        Object val;
        int hitCount;
        //        used to interrupt
        Thread timer;
        long timeOut;


        public Value(Object v, Object key) {
            this.key = key;
            this.val = v;
            this.hitCount = 1;  //第一次进入设置命中为1
        }

        public Value(Object v, Object key, long timeout) {
            this(v, key);
            this.timeOut = timeout;
        }


        public void renewTimeOutTask(Object key, long timeOut) {
            this.timer.interrupt();
            this.timeOut = timeOut;
            if (this.timer.isInterrupted()) {
                this.createTimeoutTask(key);
            }
        }

        public void createTimeoutTask(Object key) {
//            this.timer = Util.setTimeout(() -> {
//                this.val = null;
//            }, this.timeOut);
            this.timer = Util.setTimeout(() -> {
                cacheMap.remove(key);
            }, this.timeOut);
        }

        public void setVal(Object obj) {
            this.val = obj;
            this.createTimeoutTask(key);
        }

        public void countIncrement() {
            hitCount++;
        }

        @Override
        public int compareTo(Value o) {
            if (o instanceof Value) {  //如果比较的类属于Value或者是Value的子类
                Value v = (Value) o;
                if (this.hitCount > v.hitCount)
                    return 1;
                else
                    return -1;
            }
            return 0;
        }
    }
}
