package com.example;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class Storage<K, V> {

    public static final int cacheSizeLimit = 1000000;


    private static final ScheduledExecutorService scheduledClearTask = Executors.newSingleThreadScheduledExecutor();


    private static final Map<Long, List> sortMap = new TreeMap<>((o1, o2) -> (int) (o1 - o2));
    private static Map<String, Node> storageMap = new ConcurrentHashMap<>();

    private static void startClearTask() {
        scheduledClearTask.scheduleAtFixedRate(new ExpireThread(), 100, 100, TimeUnit.MILLISECONDS);
    }

    public String get(String key) throws NullPointerException {
        if (key == null) {
            return null;
        }

        // todo 根据timeOut再次清除 timerTree 和 storageMap
        return storageMap.get(key).val;
    }

    public void put(String key, String val, long timeOut) {

        storageMap.put(key, new Node(key, val, timeOut, (new Date())));
    }


    static class Node {
        String key;
        String val;
        long timeOut;
        // lastUpdatedTime + timeOut > now() ? "canGet" : "noGet and make it invalid"
        Date lastUpdatedTime;

        Date createdTime;
        private Integer hitCount;

        public Node(String key, String val, long timeOut, Date lastUpdatedTime) {
            this.key = key;
            this.val = val;
            this.timeOut = timeOut;
            this.lastUpdatedTime = lastUpdatedTime;
        }

        /**
         * 执行过期操作
         *
         * @param entry 明细
         * @since 0.0.3
         */
        static void deleteExpiredKey(Map.Entry<String, Node> entry) {
            final String key = entry.getKey();
            final Node expireAt = entry.getValue();
            // 删除的逻辑处理
            long currentTime = System.currentTimeMillis();
            Node n = storageMap.get(key);
            if (currentTime >= (n.lastUpdatedTime.getTime() + n.timeOut)) {
                storageMap.remove(key);
                // 再移除缓存，后续可以通过惰性删除做补偿
                sortMap.remove(key);
            }
        }


    }

    /**
     * 定时执行任务
     *
     * @since 0.0.3
     */
    private static class ExpireThread implements Runnable {
        @Override
        public void run() {
            //1.判断是否为空
            if (storageMap == null) {
                return;
            }
            //2. 获取 key 进行处理
            int count = 0;
            for (Map.Entry<String, Node> entry : storageMap.entrySet()) {

                Node.deleteExpiredKey(entry);
                count++;
            }
        }
    }

}
