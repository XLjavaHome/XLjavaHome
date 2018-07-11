package com.xl.base;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA. 在void里并不能改变boolean的值，只有通过返回值。
 *
 * @author: 徐立
 * @Date: 2018-03-05
 * @Time: 17:56
 * To change this template use File | Settings | File Templates.
 */
public class BooleanTest {
    @Test
    public void demoTest() {
        boolean flag = true;
        changeStatus(flag);
        System.out.println(flag);
        Boolean flag2 = true;
        flag2 = changeStatus(flag2);
        System.out.println(flag2); // /true
    }

    private boolean changeStatus(boolean flag) {
        if (flag) {
            flag = false;
        }
        return flag;
    }
}
