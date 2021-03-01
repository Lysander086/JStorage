package com.example.entity;

import com.example.Config;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pair {
    private String key;
    private String val;
    private long timeOut = Config.DEFAULT_TIMEOUT;

}
