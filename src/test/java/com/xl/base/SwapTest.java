package com.xl.base;

import org.junit.Test;

/**
 * Created with 徐立.值交换
 *
 * @author: 徐立
 * @Date: 2018-11-21
 * @Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public class SwapTest {
    int y = 111;
    private int x = 2001;
    
    /**
     * 无论传Integer，还是int都无法交换
     */
    @Test
    public void demo1Test() {
        int a = 1;
        int b = 2;
        System.out.printf("before a=%s,b=%s", a, b);
        swap(a, b);
        System.out.println();
        System.out.printf("after a=%s,b=%s", a, b);
        swap2(a, b);
        System.out.println(String.format("after a=%s,b=%s", a, b));
    }
    
    void swap(Integer a, Integer b) {
        Integer tem = a;
        a = b;
        b = tem;
    }
    
    void swap2(int a, int b) {
        int tem = a;
        a = b;
        b = tem;
    }
}
