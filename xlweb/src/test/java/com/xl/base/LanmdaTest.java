package com.xl.base;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-04-18
 * @Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
public class LanmdaTest {
    @Test
    public void demoTest() {
        LanmdaInterface t1 = (s, i) -> System.out.println("ces");
        t1.test("11", 33);
        LanmdaInterface2 t2 = () -> System.out.println("t2");
       t2.test();
        //函数试编程
        new Thread(() -> System.out.println("测试")).run();
    }
}
