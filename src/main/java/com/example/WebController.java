package com.example;

import com.example.entity.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class WebController {
    private static final Logger log = LoggerFactory.getLogger(WebController.class);
    @Resource
    LFUCache cacheStore;

    /*
    use postman
    {
        "key": "f",
        "val": "fly",
        "timeOut": 10000
    }
     */
    @PostMapping("/set")
    public String set(@RequestBody Pair pair) {
        cacheStore.put(pair.getKey(), pair.getVal(), pair.getTimeOut());
        return "success";
//        return cacheStore.showList();
    }

    /*
     use postman
    {
        "key": "f",
    }
     */
    @PostMapping("/get")
    public String get(@RequestBody Pair pair) {
        String res;
        try {
            res = (String) cacheStore.get(pair.getKey());
            log.info(res);
        } catch (Exception e) {
            res = "";
            log.info(String.valueOf(e) + " " + res);
        }
        return res;
    }



}
