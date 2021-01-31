package com.example.controlller;

import com.example.entity.LFUCacheMap;
import com.example.entity.PairDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
    @Resource
    LFUCacheMap cacheStore;

    @RequestMapping("/say")
    public String say() {
//        log.info("in say");
        return "Hello World\n line feed?";
    }

    /*
    use postman
    {
        "key": "f",
        "val": "fly"
    }
     */
    @PostMapping("/set")
    public String set(@RequestBody PairDto pair) {
        cacheStore.put(pair.getKey(), pair.getVal(), pair.getTimeOut());
        return cacheStore.showList();
    }

    @PostMapping("/get")
    public String get(@RequestBody PairDto pair) {
        String res = "null";
        try {
            res = (String) cacheStore.get(pair.getKey());
        } catch (Exception e) {
            log.info(String.valueOf(e));
        }
        return res;

    }


    @GetMapping("/all")
    public String all() {
        log.info("all");
//        return "test";
        return cacheStore.showList();
    }
}
