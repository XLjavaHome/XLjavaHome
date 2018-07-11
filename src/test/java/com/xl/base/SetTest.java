package com.xl.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
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

    @Before
    public void before() {
        s.add("ces");
        s.add("ces");
        s.add("ces2");
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
     */
    @Test
    public void listTest() {
        ArrayList<String> strings = new ArrayList<>(s);
        System.out.println(strings);
    }
}
