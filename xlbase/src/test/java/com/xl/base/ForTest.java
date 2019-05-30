package com.xl.base;

import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-30
 * @time 9:19
 * To change this template use File | Settings | File Templates.
 */
public class ForTest {
    @Test
    public void forTest() {
        System.out.println("开始了");
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
        System.out.println("测试for循环");
    }
}
