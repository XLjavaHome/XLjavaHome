package com.xl.base;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-03-14
 * @Time: 10:21
 * To change this template use File | Settings | File Templates.
 */
public class CompareTest {
    @Test
    public void compareTest() {
        //结果：NN-1
        System.out.println(Double.valueOf(0).compareTo(2.0));
        //结果：1
        System.out.println(Double.valueOf(3).compareTo(2.0));
    }
}
