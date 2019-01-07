package com.xl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2019-01-10
 * @Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
public class IteratorTest {
    /**
     * 测试
     */
    @Test
    public void listTest() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            iterator.remove();
        }
        System.out.println(list);
    }
}
