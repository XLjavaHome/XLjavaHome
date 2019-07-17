package com.xl.base;

import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-16
 * @time 23:14
 * To change this template use File | Settings | File Templates.
 */
public class HashTest {
    @Test
    void name() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        System.out.println("obj1哈希值:" + obj1.hashCode());
        System.out.println("obj2哈希值:" + obj2.hashCode());
        System.out.println("obj1和obj2比较==：" + obj1.equals(obj2));
        System.out.println("obj1和obj2比较equals：" + obj1.equals(obj2));
        String str1 = "123";
        String str2 = "123";
        String str3 = new String("123");
        String str4 = new String("123");
        System.out.println("str1哈希值:" + str1.hashCode());
        System.out.println("str2哈希值:" + str2.hashCode());
        System.out.println("str3哈希值:" + str3.hashCode());
        System.out.println("str4哈希值:" + str4.hashCode());
        System.out.println("str1和str2比较==：" + (str1 == str2));
        System.out.println("str1和str3比较==：" + (str1 == str3));
        System.out.println("str4和str3比较==：" + (str4 == str2));
        System.out.println("str1和str2比较equals：" + str1.equals(str2));
        System.out.println("str1和str3比较equals：" + str1.equals(str3));
        System.out.println("str4和str3比较equals：" + str4.equals(str3));
        Integer int1 = new Integer(100);
        Integer int2 = new Integer(100);
        System.out.println("包装类型int1哈希值:" + int1.hashCode());
        System.out.println("包装类型int2哈希值:" + int2.hashCode());
        System.out.println("包装类型int1和int2比较==：" + (int1 == int2));
        System.out.println("包装类型int1和int2比较equal：" + (int1.equals(int2)));
        int a = 5;
        int b = 5;
        System.out.println("a和b比较==：" + (a == b));
    }
}
