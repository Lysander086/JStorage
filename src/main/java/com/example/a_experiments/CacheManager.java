package com.example.a_experiments;

import lombok.Data;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * - 不可变对象
 * - 单例
 * - 线程安全
 * - 回收失效数据
 * - 垃圾回收
 * - 缓存大小
 * - LRU算法
 */
public class CacheManager {

    private final static Map<Object, CacheEntry> cacheEntryMap = new ConcurrentHashMap<>();

    //缓存使用记录
    private static LinkedList<Object> cacheUseRecord = new LinkedList<>();
    //可缓存最大数量
    private static Long MAX_CACHE_SIZE = 100 * 1000 * 1000L;

    //重入读写锁
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static Lock writeLock = reentrantReadWriteLock.writeLock();
    private static Lock readLock = reentrantReadWriteLock.readLock();


    //是否开启清除失效缓存
    private volatile Boolean clearExpireCacheEnable = true;

    //缓存失效时间
    private long cacheTimeout = 10 * 1000L;
//    private long cacheTimeout = 12 * 60 * 60 * 1000L;


    private CacheManager() {
    }

    private static CacheManager getCacheManagerInstance() {
        return CacheManagerFactory.CACHE_MANAGER;
    }

    //更新缓存访问时间
    public static void touchCache(CacheEntry entry) {
        writeLock.lock();
        try {
            entry.setLastTouchTime(System.currentTimeMillis());
        } finally {
            writeLock.unlock();
        }

    }

    //更新缓存使用记录
    public static void touchUseRecord(Object key) {

        writeLock.lock();
        try {
            //删除使用记录
            cacheUseRecord.remove(key);
            //新增使用记录到首位
            cacheUseRecord.add(0, key);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 删除最近最久未使用的缓存
     */
    public static void deleteLRU() {

        Object cacheKey = null;
        writeLock.lock();
        try {
            cacheKey = cacheUseRecord.remove(cacheUseRecord.size() - 1);
            cacheEntryMap.remove(cacheKey);
            System.out.println("LRU清除元素key：" + cacheKey);
        } finally {
            writeLock.unlock();
        }
    }

    public static void delete(Object key) {

        if (null == key)
            return;

        writeLock.lock();
        try {
            cacheEntryMap.remove(key);
            cacheUseRecord.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public static void clear() {

        writeLock.lock();
        try {
            cacheEntryMap.clear();
            cacheUseRecord.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        CacheManager cacheManager = CacheManager.getCacheManagerInstance();
        cacheManager.init(0);

        for (int i = 0; i < 200; i++) {
            cacheManager.put(i + "", i);
        }
    }

    private void init() {
        initClearTask();
    }

    //自定义缓存失效时间
    private void init(long cacheTimes) {
        this.cacheTimeout = cacheTimes;
        initClearTask();
    }

    private void initClearTask() {
        //启动清除失效缓存数据
        if (clearExpireCacheEnable) {
            new ClearCacheTask().start();
        }
    }

    public Object get(Object key) {

        readLock.lock();
        CacheEntry entry = null;
        try {
            entry = cacheEntryMap.get(key);
        } finally {
            readLock.unlock();
        }
        if (null == entry)
            return null;

        //更新缓存访问时间
        touchCache(entry);
        //更新使用记录
        touchUseRecord(key);

        return entry == null ? null : entry.value;
    }

    public Object put(Object key, Object value) throws Exception {

        //判断缓存大小是否够用，否则根据LRU删除最久未使用的元素
        if (cacheEntryMap.size() > MAX_CACHE_SIZE) {
            deleteLRU();
        }
        if (cacheEntryMap.size() > MAX_CACHE_SIZE) {
            throw new Exception("缓存大小超出限制");
        }

        CacheEntry entry = new CacheEntry(value);

        writeLock.lock();
        try {
            cacheEntryMap.put(key, entry);
            cacheUseRecord.add(0, key);
        } finally {
            writeLock.unlock();
        }
        return value;
    }

    private static class CacheManagerFactory {
        private static final CacheManager CACHE_MANAGER = new CacheManager();
    }

    private class ClearCacheTask extends Thread {

        ClearCacheTask() {
            super.setName("clear cache task start ...");
        }

        @Override
        public void run() {
            while (clearExpireCacheEnable) {
                try {
                    long now = System.currentTimeMillis();

//                    //定时清理
                    try {
                        sleep(1000 * 60 * 60);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    cacheEntryMap.keySet().stream().forEach(key -> {
                        try {
                            writeLock.lock();

                            //判断使用记录中的key是否已经被LRU清除
                            if (!cacheUseRecord.contains(key)) {
                                return;
                            }

                            CacheEntry entry = cacheEntryMap.get(key);
                            if (now - entry.lastTouchTime >= cacheTimeout) {
                                cacheEntryMap.remove(key);
                                cacheUseRecord.remove(key);
                                System.out.println("清理缓存key：" + key);

                            }
                        } finally {
                            writeLock.unlock();
                        }
                    });

                    Thread.sleep(cacheTimeout);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //失效时间，value，entry，可根据需要决定是否继承Map.Entry<K,V>
    @Data
    private class CacheEntry {
        long lastTouchTime;

        Object value;

        CacheEntry(Object value) {
            super();
            this.value = value;
            this.lastTouchTime = System.currentTimeMillis();
        }
    }
}
