package com.xl.collections;

import com.xl.util.MapUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Test;

/**
 * Created with 徐立.转换成流再进行排序
 *
 * @author: 徐立
 * @Date: 2019-01-29
 * @Time: 10:56
 * To change this template use File | Settings | File Templates.
 */
public class MapSortTest {
    /**
     *
     */
    @Test
    public void sortTest() {
        Map<String, Integer> sortMap = new HashMap<>();
        sortMap.put("z", 10);
        sortMap.put("4", 32);
        sortMap.put("b", 5);
        sortMap.put("a", 6);
        sortMap.put("2", 32);
        sortMap.put("c", 20);
        sortMap.put("d", 1);
        sortMap.put("e", 7);
        sortMap.put("y", 8);
        sortMap.put("n", 99);
        sortMap.put("g", 50);
        sortMap.put("m", 2);
        sortMap.put("f", 9);
        TreeMap treeMap = MapUtil.sortMap(sortMap);
        System.out.println(treeMap);
    }
}
