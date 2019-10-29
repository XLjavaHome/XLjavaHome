package com.xl.base;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-09
 * @time 17:33
 * To change this template use File | Settings | File Templates.
 */
public class SetTest {
    /**
     * TreeSet/HashSet
     * 1、TreeSet背后的结构是TreeMap，也就是红黑树，能够实现自动排序。它通过equals方法或者compareTo方法进行内容的比较。
     * 2、HashSet背后是HashMap，key是无序的，只能做外部排序。既然是Hash，那么就要重写其中对象的hashCode和equals方法
     * 另外，还有个细微的差别可以拿来装b：
     * 1、HashSet可以接受null值，有且只有一个
     * 2、TreeSet默认不可以接受null值，会直接抛出空指针异常
     */
    @Test
    public void name() {
    }
    
    @Test
    public void sortTest() {
        Set<Integer> seen = ConcurrentHashMap.newKeySet();
        seen.add(1);
        seen.add(2);
        System.out.println(seen);
    }
}
