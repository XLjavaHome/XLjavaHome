package com.xl.base;

import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-25
 * @time 9:42
 * To change this template use File | Settings | File Templates.
 */
public class FormatTest {
    @Test
    void stringFormat() {
        String[] obj = {"第一个参数", "第二个参数", "第三个参数"};
        //没有多余的占位符不会报错
        System.out.println(String.format("%1$s %2$s", obj));
    }
}
