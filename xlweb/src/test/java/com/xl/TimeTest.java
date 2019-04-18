package com.xl;

import java.time.Clock;
import java.time.LocalDateTime;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-07-19
 * @Time: 12:33
 * To change this template use File | Settings | File Templates.
 */
public class TimeTest {
    @Test
    public void demoTest() {
        //2018-07-19T12:58:50.608
        LocalDateTime now = LocalDateTime.now(Clock.systemDefaultZone());
        System.out.println(now);
    }
    
    @Test
    public void demo2Test() {
        //System.out.println(System.currentTimeMillis());
    }
    
    @Test
    public void demo3Test() {
        System.out.println("3333");
    }
}
