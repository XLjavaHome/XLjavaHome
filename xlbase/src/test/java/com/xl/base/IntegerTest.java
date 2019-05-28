package com.xl.base;

import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-22
 * @time 15:14
 * To change this template use File | Settings | File Templates.
 */
public class IntegerTest {
    @Test
    public void 空测试() {
        Integer a = 4;
        //可以这样写
        System.out.println(a == null);
        //Integer的toString返回值
        System.out.println(a.toString());
    }
}
