package com.example.entity;

import com.example.Config;
import lombok.Data;

@Data
public class PairDto {
    private String key;
    private String val;
    private long timeOut = Config.DEFAULT_TIMEOUT;



}
