package com.xl.base;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-22
 * @time 10:07
 * To change this template use File | Settings | File Templates.
 */
public class MapTest {
    @Test
    void caseInsensitiveMap() {
        CaseInsensitiveMap map = new CaseInsensitiveMap();
        map.put("hello", 1);
        map.put("Hello", 2);
        System.out.println(map);
    }
    
    /**
     * 1. 可以保存map中没有的key
     * 2. 如果已有则执行第3个参数 lamda表达式
     * 3. 第三个参数 增强函数BiFunction<T, U, R>  旧value，新value
     */
    @Test
    void merge() {
        Map<Integer, String> map = init();
        System.out.println(map);
    }
    
    private HashMap<Integer, String> init() {
        HashMap<Integer, String> map = new HashMap<>(10);
        map.put(1, "张三");
        map.merge(2, "李四", (s, s2) -> s + "、" + s2);
        map.merge(2, "张三", (s, s2) -> s + "、" + s2);
        map.merge(3, "张三", (s, s2) -> s + "、" + s2);
        return map;
    }
    
    /**
     * 遍历不用ketSet而用entrySet
     */
    @Test
    void forTest() {
        //只需要key，keySet更高效
        HashMap<Integer, String> map = init();
        for (Integer key : map.keySet()) {
            String value = map.get(key);
            System.out.println(value);
        }
        //同时需要key，value map.entrySet更高效
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
