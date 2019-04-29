package com.xl.base;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

@Log4j
public class IntegerTest {
    @Test
    public void stringToInteger() {
        String s = "123.s12.3";
        char[] chars = s.toCharArray();
        for (Character aChar : chars) {
            boolean digit = Character.isDigit(aChar);
            System.out.println(digit);
        }
    }
    
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
    
    /**
     * 无论是包装类 还是基本类型都不改变值
     */
    @Test
    public void changeTest() {
        Boolean flag = false;
        changeFlag(flag);
        System.out.println(flag + "");
    }
    
    private void changeFlag(Boolean flag) {
        flag = true;
    }
    
    /**
     * 都会触发空指针异常
     */
    @Test
    public void nullEqualTest() {
        Integer a = null;
        //Integer 等于""没有意义
        System.out.println(a.equals(""));
        System.out.println(a == 2);
        System.out.println(3 == a);
    }
    
    @Test
    public void EqualTest() {
        Integer a = 129;
        Integer b = 129;
        //相等是false
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
