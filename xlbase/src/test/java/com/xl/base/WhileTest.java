package com.xl.base;

import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-14
 * @time 22:54
 * To change this template use File | Settings | File Templates.
 */
public class WhileTest {
    /**
     * 至少执行1次
     */
    @Test
    public void dowhile() {
        boolean flag = true;
        do {
            System.out.println("1111");
            flag = false;
        } while (flag);
    }
}