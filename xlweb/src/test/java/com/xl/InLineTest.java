package com.xl;

/**
 * Created with 徐立.内联
 * 1.选择区域 add(one,two)；
 * 2.按ctrl+alt+N.可以将方法体的方法替换该方法
 *
 * @author: 徐立
 * @Date: 2018-12-26
 * @Time: 13:08
 * To change this template use File | Settings | File Templates.
 */
public class InLineTest {
    public void method() {
        int one = 1;
        int two = 2;
        int three = add(one, two);
        int four = add(one, three);
    }
    
    private int add(int one, int two) {
        return one + two;
    }
}
