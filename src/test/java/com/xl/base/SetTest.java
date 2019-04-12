package com.xl.base;

import java.util.*;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections4.SetUtils;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-04-23
 * @Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class SetTest {
    Set<String> s = new HashSet<String>(10);
    
    public SetTest() {
        s.add("ces");
        s.add("ces");
        s.add("ces2");
        s.add("ces6");
        s.add("ces3");
    }
    
    @Test
    public void demoTest() {
        s.add("ces");
        s.add("ces");
        s.add("ces2");
        List<String> list = new ArrayList<String>();
        list.addAll(s);
        log.info(list);
    }
    
    /**
     * 可以加空
     */
    @Test
    public void nullTest() {
        s.add(null);
        s.add(null);
        s.add("22");
        System.out.println(s);
    }
    
    /**
     * set集合转化为list集合
     * list转set
     */
    @Test
    public void listTest() {
        List<String> list = new ArrayList<>(100);
        for (int i = 0; i < 5; i++) {
            list.add(i + "");
        }
        list.add(3 + "");
        System.out.println(list);
        System.out.println(new HashSet<>(list));
        ArrayList<String> strings = new ArrayList<>(s);
        System.out.println(strings);
    }
    
    /**
     * 测试简单交集
     */
    @Test
    public void intersection() {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        set1.add("a");
        set1.add("b");
        set1.add("c");
        set2.add("c");
        set2.add("d");
        set2.add("e");
        //交集
        set1.retainAll(set2);
        System.out.println("交集是 " + set1);  //交集是 [c]
    }
    
    /**
     * 差集测试
     */
    @Test
    public void testDifferenceSet() {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        set1.add("a");
        set1.add("b");
        set1.add("c");
        set1.add("d");
        set2.add("c");
        set2.add("d");
        set2.add("e");
        set2.add("f");
        set1.removeAll(set2);
        System.out.println("差集是 " + set1); //差集是 [a, b]
    }
    
    /**
     * 包含
     */
    @Test
    public void containTest() {
        Set<String> set1 = new HashSet<>();
        set1.add("1");
        set1.add("2");
        Set<String> set2 = new HashSet<>();
        set2.add("1");
        set2.add("3");
        //true
        System.out.println(contain(set1, set2));
        //false
        System.out.println(set1.containsAll(set2));
    }
    
    private boolean contain(Set<String> set1, Set<String> set2) {
        for (String string : set2) {
            if (set1.contains(string)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * hashset会排序
     * linkHashSet 跟加入的顺序有关
     */
    @Test
    public void setTest() {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new LinkedHashSet<>();
        initSet(set1);
        initSet(set2);
    }
    
    private void initSet(Set<Integer> set) {
        set.add(1);
        set.add(3);
        set.add(2);
    }
    
    /**
     * 
     */
    @Test
    public void sortTest() {
        System.out.println(s);
        Set<String> newSet = SetUtils.orderedSet(s);
        System.out.println(s);
        System.out.println(newSet);
    }
}
