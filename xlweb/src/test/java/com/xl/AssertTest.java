package com.xl;

import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-11-25
 * @Time: 9:59
 * To change this template use File | Settings | File Templates.
 */
public class AssertTest {
    /**
     * 设置一下jvm的参数，参数是-ea，没有设置会不起作用
     */
    @Test
    public void assertTest() {
        assert 1 == 1;
        assert 1 == 2;
        System.out.println("断言测试");
    }
}
