package com.xl.base;

import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-12-27
 * @Time: 13:37
 * To change this template use File | Settings | File Templates.
 */
public class StringBuilderTest {
    /**
     * 结尾测试
     */
    @Test
    public void endTest() {
        //length是7，index值是6 和lastIndexOf结果一样
        StringBuilder first = new StringBuilder("这是结尾测试。");
        StringBuilder endTest2 = new StringBuilder("这是结尾测试");
        System.out.println(first.length());
        System.out.println(first.lastIndexOf("。"));
        System.out.println(first.indexOf("。"));
        System.out.println(endTest2.lastIndexOf("。"));
    }
}
