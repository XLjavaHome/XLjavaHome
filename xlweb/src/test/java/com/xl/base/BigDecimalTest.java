package com.xl.base;

import java.math.BigDecimal;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2017-11-28
 * @Time: 17:30
 * To change this template use File | Settings | File Templates.
 */
public class BigDecimalTest {
    /**
     * 不能加空，否则会异常
     */
    @Test
    public void demoTest() {
        BigDecimal a = new BigDecimal(1.1);
        System.out.println(a.add(new BigDecimal(2)));
        System.out.println(a.add(new BigDecimal(2)).doubleValue());
        System.out.println(a);
        System.out.println(a.doubleValue());
    }
    
    /**
     * 零测试
     */
    @Test
    public void lingTest() {
        BigDecimal a = new BigDecimal("0");
        BigDecimal b = new BigDecimal(0);
        System.out.println(a);
        System.out.println(b);
        System.out.println(a.doubleValue());
    }
    
    /**
     * 除
     */
    @Test
    public void exceptTest() {
        BigDecimal a = new BigDecimal("15");
        BigDecimal b = new BigDecimal(5);
        System.out.println(a.divide(b));
    }
}
