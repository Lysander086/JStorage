package com.example.a_experiments;

import com.example.entity.Pair;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class PlayJava {

    @SneakyThrows
    public static void main(String[] args) {
        TreeMap<Long, Pair> tm = new TreeMap();
        long kTime = System.currentTimeMillis();

        long leftK = 1614781620860L;

        tm.put(leftK, new Pair("e", "eye"));

        setAccessible(TreeMap.class);
        tm.put(kTime, new Pair("f", "fly"));

        Method getEntry = TreeMap.class.getDeclaredMethod("getEntry", Object.class);

        getEntry.setAccessible(true);

        Map.Entry res = (Map.Entry) getEntry.invoke(tm, kTime);

//
//        System.out.println(res.getClass().toString());
//
//        System.out.println(res.getKey().toString());
//
//        System.out.println(res.getValue().getClass());

//        System.out.println(res.left);

    }


    public static void setAccessible(Class clazz) {
        setAccessible(clazz, true, true);
    }

    public static void setAccessible(Class clazz, boolean toSetMethods, boolean toSetFields) {

        if (toSetMethods) {
            Method[] methods = clazz.getDeclaredMethods();

            for (Method m : methods) {
                m.setAccessible(true);
            }
        }

        if (toSetFields) {
            Field[] fields = TreeMap.class.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

            }


        }


    }


}
