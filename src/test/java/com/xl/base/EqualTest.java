package com.xl.base;

import java.util.ArrayList;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-24
 * @Time: 16:18
 * To change this template use File | Settings | File Templates.
 */
public class EqualTest {
    @Test
    public void equalTest() {
        //测试两个对象是否相同
        String s1 = new String("javaisland");
        System.out.println(s1 instanceof String); // true
        //测试是否对象属于某个类
        String s2 = new String("javaisland");
        // true
        System.out.println(String.class.isInstance(s2));
        // false
        System.out.println(ArrayList.class.isAssignableFrom(Object.class));
        // true
        System.out.println(Object.class.isAssignableFrom(ArrayList.class));
    }

    @Test
    public void BigDicmalQqualTest() {
        //false
        System.out.println("1".equals(1));
        Integer flag = 1;
        //false
        System.out.println(flag.equals("1"));
    }
}
