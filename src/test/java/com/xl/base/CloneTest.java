package com.xl.base;

import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-10-16
 * @Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
public class CloneTest implements Cloneable {
    @Test
    public void demoTest() throws CloneNotSupportedException {
        this.clone();
    }
}
