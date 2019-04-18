package com.xl;

import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-12-22
 * @Time: 20:46
 * To change this template use File | Settings | File Templates.
 */
public class HashCodeTest {
    /**
     * hasoCode
     */
    @Test
    public void Test() {
        for (int i = 0; i < 100; i++) {
            String s = "测试" + i;
            System.out.println(s.hashCode());
        }
    }
}
