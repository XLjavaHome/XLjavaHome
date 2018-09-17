package com.xl.util;

import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-28
 * @Time: 20:46
 * To change this template use File | Settings | File Templates.
 */
public class MapUtil {
    /**
     * 根据map的value获取map的key
     *
     * @param map
     * @param value
     * @return
     */
    public static String getKey(Map<String, String> map, String value) {
        String key = null;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                key = entry.getKey();
            }
        }
        return key;
    }

    /**
     * 打印map
     *
     * @param map
     */
    public static void print(Map map) {
        Iterator i = map.keySet().iterator();
        while (i.hasNext()) {
            Object key = i.next();
            System.out.println(String.format("%s:%s", key, map.get(key)));
        }
    }
}
