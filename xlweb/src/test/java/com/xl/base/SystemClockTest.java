package com.xl.base;

import org.junit.Test;

import java.time.Clock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-03-26
 * @Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
public class SystemClockTest {
    @Test
    public void demoTest() {
        //输出SystemClock[Z]
        System.out.println(Clock.systemUTC());
    }
}
