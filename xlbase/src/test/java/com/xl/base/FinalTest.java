package com.xl.base;

import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-14
 * @time 17:50
 * To change this template use File | Settings | File Templates.
 */
public class FinalTest {
    @Test
    public void 测试参数加final有什么区别() {
        StringBuilder a = new StringBuilder("张三");
        final StringBuilder b = new StringBuilder("李四");
        testParam(a, b);
        String x = "张三";
        String y = "李四";
        System.out.println(a);
        System.out.println(b);
        testParam2(x, y);
    }
    
    /**
     * final还是会追加
     *
     * @param a
     * @param b
     */
    private void testParam(StringBuilder a, final StringBuilder b) {
        a.append("测试");
        b.append("测试");
    }
    
    /**
     * 对y进行更改会报错
     *
     * @param x
     * @param y
     */
    private void testParam2(String x, final String y) {
        x += "测试";
    }
}
