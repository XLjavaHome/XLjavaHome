package com.xl.base;

import com.xl.util.MethodUtil;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-08
 * @Time: 9:19
 * To change this template use File | Settings | File Templates.
 */
public class MethodTest {
    @Test
    public void methodNameTest() {
        System.out.println(MethodUtil.getMethodName3());
        System.out.println(MethodUtil.getMethodName2());
    }
}
