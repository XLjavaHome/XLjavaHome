package com.xl.base;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

@Log4j
public class IntegerTest {
    /**
     * Integer会缓存-127到127的数据
     */
    @Test
    public void IntegerDemo() {
        int max = Integer.MAX_VALUE;
        System.out.println(max);
        int min = Integer.MIN_VALUE;
        System.out.println(min);
        Integer a = 1000, b = 1000, c = 100, d = 100;
        Integer x = 129, y = 129;
        //不行等 false
        System.out.println(x == y);
        //true
        System.out.println(x.intValue() == y.intValue());
        //true
        System.out.println(x == 129);
        System.out.println(a == b); //false
        System.out.println(c == d); //true
    }
    
    @Test
    public void emptyTest() {
        Integer a = null;
        //会报空指针异常
        if (a == 2) {
            System.out.println(a);
        }
    }
    
    /**
     * 比较测试
     */
    @Test
    public void compareTest() {
        //a 比b大就返回1
        Integer a = 8, b = 9;
        System.out.println(a.compareTo(b));
    }
}
