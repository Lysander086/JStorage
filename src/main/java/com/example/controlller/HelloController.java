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
        "val": "fly",
        timeOut: 10000
    }
     */
    @PostMapping("/set")
    public String set(@RequestBody PairDto pair) {
        cacheStore.put(pair.getKey(), pair.getVal(), pair.getTimeOut());
        return cacheStore.showList();
    }

    /*
     use postman
    {
        "key": "f",
    }
     */
    @PostMapping("/get")
    public String get(@RequestBody PairDto pair) {
        String res;
        try {
            res = String.valueOf(cacheStore.get(pair.getKey()));
        } catch (Exception e) {
            res = "null";
            log.info(String.valueOf(e) + " " + res);
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
