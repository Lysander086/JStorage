package com.example.service;

import com.example.entity.MyTreeMap;
import com.example.entity.Pair;
import com.example.util.MapUtil;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class SolutionCache {

    /**
     * 定期清理线程
     */
    private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();
    // data storage
    private static Map<String, String> cacheMap = new ConcurrentHashMap<>();
    // for scheduled task
    private static Map<Integer, Pair> treeMap = new MyTreeMap<>();


    public SolutionCache() {
        this.initScheduledCleaningTask();
    }

    /**
     * 初始化定期清理线程
     */
    private void initScheduledCleaningTask() {

    }

    private class ScheduledExpireTask implements Runnable {
        @Override
        public void run() {
            if (MapUtil.isEmpty(treeMap)) return;

            long currentValidTime = System.currentTimeMillis();


        }

        private void findNode(long keyToFind){

        }

    }

}
