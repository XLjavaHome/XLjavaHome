package com.xl.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * Date: 2017-11-20
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
public class CollectionsTest {
    @Test
    public void shuffleDemo() {
        List<String> list = new ArrayList<String>();
        list.add("abcds");
        list.add("aaa");
        list.add("kk");
        list.add("c");
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
    }

    /*
     * 练习：fill方法可以将list集合中所有元素替换成指定元素。 将list集合中部分元素替换成指定元素。
     */
    @Test
    public void replaceAllDemo() {
        List<String> list = new ArrayList<String>();
        list.add("asddfg");
        list.add("asdfg");
        list.add("asdfassafg");
        list.add("aa");
        list.add("asdfg");
        System.out.println(list);
        Collections.replaceAll(list, "aa", "qq"); // 将list中的aa替换成qq
        System.out.println(list);
        Collections.reverse(list);
    }

    @Test
    public void fillDemo() {
        List<String> list = new ArrayList<String>();
        list.add("asdfg");
        list.add("asdfg");
        list.add("asdfg");
        list.add("asdfg");
        list.add("asdfg");
        list.add("asdfg");
        list.add("asdfg");
        System.out.println(list);
        Collections.fill(list, "qq"); // 将list集合所有的元素替换成qq
        System.out.println(list);
    }
}
