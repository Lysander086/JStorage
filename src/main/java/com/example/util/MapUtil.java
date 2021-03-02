//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.util;

import java.util.Map;

public final class MapUtil {
    private MapUtil() {
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || 0 == map.size();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }


}
