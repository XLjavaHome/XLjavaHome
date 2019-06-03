package com.xl.base;

import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-03
 * @time 17:58
 * To change this template use File | Settings | File Templates.
 */
public class FinalTest {
    @Test
    public void test1() {
        String a = "hello2";
        //final b变成常量
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println(c);
        System.out.println(e);
        //true
        System.out.println((a == c));
        //false
        System.out.println((a == e));
    }
}
