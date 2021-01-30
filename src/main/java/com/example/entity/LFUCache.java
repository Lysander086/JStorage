package com.example.entity;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 最不常使用 (Least Frequently Used)
 * - 最不常使用的排在最后面 ( 根据引用次数排序 )
 */
// LFU算法其实还要考虑命中时间, 本实现先考虑命中次数
@Service
public class LFUCache {

    private Map<Object, Value> map = new HashMap<>();


    //获取缓存中的数据
    public Object get(Object key) {
        if (key == null)
            return null;

        //命中+1
        map.get(key).countInc();
        return map.get(key).val;
    }

    //存储数据
    public void put(Object key, Object val) {
        //如果本来就存在
        if (map.get(key) != null) {
            map.get(key).countInc();//命中计数
            map.get(key).setVal(val);//覆盖结果值
        } else {
            //如果存储已超过限定值
//            if (map.size() >= SIZE) {
//                remove();//移除最后一个数据
//            }
            Value value = new Value(val, key);
            map.put(key, value);
        }


        //
    }

    // unused: 数据移除最后一个数据
    public void remove() {
        //先拿到最后一个数据
        Value v = Collections.min(map.values());
        //移除最后一个数据
        map.remove(v.key);
    }

    //获取存储情况
    public String showList() {
        List<Value> list = new ArrayList<>(map.values());
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

        public Value(Object v, Object key) {
            this.key = key;
            this.val = v;
            this.hitCount = 1;  //第一次进入设置命中为1
        }

        public void setVal(Object obj) {
            this.val = obj;
        }

        public void countInc() {
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
