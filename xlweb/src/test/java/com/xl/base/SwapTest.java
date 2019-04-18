package com.xl.base;

import java.lang.reflect.Field;
import org.junit.Test;

/**
 * Created with 徐立.值交换  -128~127 (包含)
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
        System.out.println();
        System.out.println(String.format("after a=%s,b=%s", a, b));
        //达到了128就无法换位
        a = 100;
        b = -129;
        swap(a, b);
        System.out.println(String.format("after a=%s,b=%s", a, b));
    }
    
    /**
     * 用反射调用属性对象实现换位
     * 使用第三方变量
     *
     * @param a
     * @param b
     */
    void swap(Integer a, Integer b) {
        int tem = a;
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            field.set(a, b);
            //field.set(b, tem); 无法实现b赋值
            field.setInt(b, tem);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 不适用第三方变量
     *
     * @param a
     * @param b
     */
    void swap2(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }
}
