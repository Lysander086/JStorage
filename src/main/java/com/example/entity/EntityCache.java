package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EntityCache {
    //    saved data
    private Object datas;

    // test mode, timeOut= 60000, prod mode: timeOut = 10000
    private long timeOut = 60000;


    private  long lastRefreshTime;
}
