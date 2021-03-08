package com.example.service;

import com.example.entity.Pair;
import com.example.util.MapUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SolutionCache {

    /**
     * 定期清理线程
     */
    private static final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();

    // data storage
    private static Map<String, String> cacheMap = new ConcurrentHashMap<>();
    // for scheduled task
    private static Map<Long, Pair> sortedMap = new TreeMap<>();


    public SolutionCache() {
        this.initScheduledCleaningTask();
    }

    /**
     * 初始化定期清理线程
     */
    private void initScheduledCleaningTask() {

        cleaner.scheduleAtFixedRate(new ScheduledExpireTask(), 500, 500, TimeUnit.MILLISECONDS);
    }

    private class ScheduledExpireTask implements Runnable {
        @Override
        public void run() {
            if (MapUtil.isEmpty(sortedMap)) return;

            for (Map.Entry<Long, Pair> entry : sortedMap.entrySet()){
                Long expireAt = entry.getKey();
                List expireKeys = (List) entry.getValue();

                if (expireKeys.isEmpty()){
                    sortedMap.remove(expireAt);
                    continue;
                }

                long currentTime = System.currentTimeMillis();

                if(currentTime >= expireAt){
                    Iterable iterator = (Iterable) expireKeys.iterator();

//                    while (iterator.hasN)


                }


            }

        }

    }

}
