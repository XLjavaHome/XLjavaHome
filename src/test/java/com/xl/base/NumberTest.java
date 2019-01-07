package com.xl.base;

import com.xl.util.NumberTool;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.junit.Test;

public class NumberTest {
    /**
     * 数值可加下划线
     */
    int one_million = 1_000_000;
    
    @Test
    public void numberTest() {
        System.out.println(1 / 1000);
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
    
    @Test
    public void scaleTest() {
        System.out.println(new BigDecimal(123.120).setScale(1));
        System.out.println(new BigDecimal(123.120).setScale(4));
        System.out.println(new BigDecimal("123.123").setScale(2));
        System.out.println(new BigDecimal("123.128").setScale(2));
    }
    
    /**
     * 效果是一样的,会四舍五入
     */
    @Test
    public void formatTest() {
        DecimalFormat format = new DecimalFormat("##.##");
        double number = 3434324323.1274123;
        System.out.println(format.format(number));
        System.out.println(String.format("%.2f", number));
        System.out.printf("%.2f", number);
    }
    
    /**
     * 如果Integer为空的话,直接判断报错
     */
    @Test
    public void equalTest() {
        Integer s = null;
        System.out.println(NumberTool.equals(s, 44));
        s = 2;
        System.out.println(s == 2.0);
    }
    
    /**
     * 添加
     */
    @Test
    public void addTest() {
        BigDecimal bigDecimal = new BigDecimal("3");
        bigDecimal.add(BigDecimal.valueOf(4));
        //输出结果是3 ,累加要重新赋值
        System.out.println(bigDecimal);
    }
}
