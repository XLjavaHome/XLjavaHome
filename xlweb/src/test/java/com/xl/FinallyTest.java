package com.xl;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-07-18
 * @Time: 13:34
 * To change this template use File | Settings | File Templates.
 */
public class FinallyTest {
    @Test
    public void finallyTest() {
        //先输出finally中的内容关闭
        System.out.println(a());
    }

    private String a() {
        try {
            return "测试";
        } finally {
            System.out.println("关闭");
        }
    }
}
