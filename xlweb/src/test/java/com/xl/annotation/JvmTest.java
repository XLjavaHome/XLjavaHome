package com.xl.annotation;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.查看JVM
 * User: 徐立
 * Date: 2017-10-09
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 */
public class JvmTest {
    @Test
    public void jvmTest() {
        System.out.println(Runtime.getRuntime().maxMemory()); //最大可用内存，对应-Xmx
        System.out.println(Runtime.getRuntime().freeMemory());//当前JVM空闲内存
        System.out.println(Runtime.getRuntime().totalMemory());  //当前JVM占用的内存总数，其值相当于当前JVM已使用的内存及freeMemory()的总和
    }
}

