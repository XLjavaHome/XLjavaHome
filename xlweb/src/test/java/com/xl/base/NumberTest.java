package com.xl.base;

import com.xl.util.NumberTool;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

@Log4j
public class NumberTest {
    /**
     * 数值可加下划线
     */
    int one_million = 1_000_000;
    
    /**
     * 测试
     */
    @Test
    public void demoTest() {
        log.info(new BigDecimal("2.00").subtract(new BigDecimal("1.1")));
        //1是1位小数， 数字代表几位小数
        log.info(String.format("%.1f", 2.0 - 1.1));
        //不是精确计算
        log.info(2.0 - 1.1);
    }
    
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
        format.setMaximumFractionDigits(3);
        double number = 3434324323.1274123;
        System.out.println(format.format(number));
        System.out.println(String.format("%.2f", number));
        number=1.1;
        System.out.println(String.format("%.0f", number));
        //null测试空
        System.out.println(String.format("%s测试空", null));
        System.out.printf("%.2f", number);
    }
    
    /**
     * 数字格式化
     */
    @Test
    public void numberFormatTest() {
        //默认，保留3位小数 可以四舍五入
        NumberFormat instance = DecimalFormat.getInstance();
        //小数最后一位是会舍去
        instance.setMaximumFractionDigits(3);
        String format1 = instance.format(32.129542345);
        System.out.println(format1);
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
    
    /**
     * 占位符
     */
    @Test
    public void placeTest() {
        System.out.println(String.format("数字占位符%d,浮点型占位符%f", 2, 2.1));
    }
    
    /**
     * 判断数字
     */
    @Test
    public void isNaNTest() {
        String s = "12.32a";
        int i = Integer.parseInt(s);
    }
}
