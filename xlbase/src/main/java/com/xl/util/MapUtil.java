package com.xl.util;

import com.xl.comparator.StringComparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
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
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
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
    
    /**
     * 对map进行排序
     *
     * @param map
     * @return
     */
    public static TreeMap sortMap(Map map) {
        TreeMap treeMap = new TreeMap(new StringComparator());
        treeMap.putAll(map);
        return treeMap;
    }
}
