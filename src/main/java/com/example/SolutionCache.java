package com.example;

import com.example.entity.Pair;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

@Service
@NoArgsConstructor
public class SolutionCache {

    // data storage
    private static Map<String, String> cacheMap = new ConcurrentHashMap<>();

    // for scheduled task
    private static Map<Integer, Pair> treeMap = new TreeMap<>();






}
