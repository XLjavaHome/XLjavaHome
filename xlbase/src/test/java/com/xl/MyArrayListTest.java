package com.xl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-11-01
 * @time 14:32
 * To change this template use File | Settings | File Templates.
 */
public class MyArrayListTest {
    @Test
    void name() {
        //modCount 直接删除会异常
        //可以替换成 CopyOnWriteArrayList 则可以进行删除
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = (String) iterator.next();
            if (str.equals("c")) {
                list.remove(str);
            } else {
                System.out.println(str);
            }
        }
    }
}
