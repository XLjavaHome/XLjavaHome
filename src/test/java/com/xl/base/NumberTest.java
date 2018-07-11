package com.xl.base;

import com.xl.util.NumberTool;
import org.junit.Test;

public class NumberTest {
    /**
     * 数值可加下划线
     */
    int one_million = 1_000_000;

    @Test
    public void numberTest() {
        System.out.println(10 / 3);
        System.out.println(10 % 3);
        System.out.println(10 / 1000);
        System.out.println(10 % 1000);
        System.out.println(2001 / 1000);
        System.out.println(2001 % 1000);
    }

    @Test
    public void keepTest() {
        System.out.println(NumberTool.formateNumber(323.1212, "#.00"));//四舍五入了
        System.out.println(NumberTool.formateNumber(323.1262, "#.00"));
        System.out.println(NumberTool.formateNumber(323.1, "#.00"));
    }

    @Test
    public void longTest() {
        long a = 1;
        System.out.println(a);
    }

    /**
     * 有空值的话结果为空
     */
    @Test
    public void nullTest() {
        System.out.println(NumberTool.safeAdd(null, 1.2));
        System.out.println(NumberTool.safeAdd(1.6, null));
    }
}
