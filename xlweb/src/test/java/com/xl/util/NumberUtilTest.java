package com.xl.util;

import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-11-25
 * @Time: 0:00
 * To change this template use File | Settings | File Templates.
 */
public class NumberUtilTest {
    /**
     * 换位
     */
    @Test
    public void swarTest() {
        int a = 32;
        int b = 43;
        NumberUtil.swap(a, b);
        System.out.println(a + "," + b);
    }
}