package com.xl.collections;

import com.xl.util.MapUtil;
import com.xl.util.StringUtil;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * Date: 2017-11-20
 * Time: 16:14
 * To change this template use File | Settings | File Templates.
 */
public class MapTest {
    HashMap<String, String> map = new HashMap<String, String>(0);
    Map<Integer, String> sortMap = new HashMap<Integer, String>();
    
    @Before
    public void before() {
        map.put("02", "zhangsan2");
        map.put("03", "zhangsan3");
        map.put("01", "zhangsan1");
        map.put("04", "zhangsan4");
        sortMap.put(3, "测试3");
        sortMap.put(1, "测试1");
        sortMap.put(2, "测试2");
        sortMap.put(5, "测试0");
    }
    
    @Test
    public void mapTest() {
        // 获取map集合的所有键的Set集合,keySet();
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        // 有了Set集合。就可以获取其迭代器 迭代器和Set里面一致
        Iterator<Map.Entry<String, String>> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> me = it.next();
            String key = me.getKey();
            String value = me.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }
    }
    
    @Test
    public void emptyTest() {
        Map map = new HashMap(2);
        //空
        System.out.println(map.get("1") == null);
        //KEY可以为空
        System.out.println(map.get(null));
    }
    
    @Test
    public void valueTest() {
        String key = MapUtil.getKey(map, "zhangsan2");
        System.out.println(key);
    }
    
    @Test
    public void sortTest() {
        System.out.println(sortMap);
    }
    
    /**
     * 按照key值排序
     */
    @Test
    public void sort2Test() {
        Map<String, String> map = new TreeMap<>();
        map.put("11", "aaa");
        map.put("33", "ccc");
        map.put("22", "bbb");
        System.out.println(map);
    }
    
    @Test
    public void deleteTest() {
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (StringUtil.equals(next, "02") || StringUtil.equals(next, "01")) {
                //可以不要iterator = map.keySet().iterator();
                iterator.remove();
            }
        }
        System.out.println(map);
    }
    
    @Test
    public void concurrentHashMapTest() {
        //几乎每个人都会回答“是的”，然后回答HashMap的一些特性，譬如HashMap可以接受null键值和值，而Hashtable则不能；HashMap是非synchronized;HashMap很快；以及HashMap储存的是键值对等等
        map.put(null, "11");
        System.out.println(map);
        Hashtable hashTable = new Hashtable();
        hashTable.put(null, "11");
        System.out.println(hashTable);
    }
    
    /**
     * 容量测试
     */
    @Test
    public void capitalTest() {
        Map map = new HashMap();
        for (int i = 0; i < 100000; i++) {
            map.put(i, i);
        }
    }
    
    /**
     * 容量测试
     */
    @Test
    public void capitalTest2() {
        Map map = new HashMap(100000);
        for (int i = 0; i < 100000; i++) {
            map.put(i, i);
        }
        int j = 10;
        int s = 1;
    }
}
