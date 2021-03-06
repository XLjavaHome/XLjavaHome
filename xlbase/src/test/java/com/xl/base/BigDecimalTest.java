package com.xl.base;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-03
 * @time 9:11
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class BigDecimalTest {
    private BigDecimal a;
    
    @Test
    public void name() {
        BigDecimal a = new BigDecimal("2");
        BigDecimal b = new BigDecimal("3");
        System.out.println(a.add(b));
    }
    
    @Test
    public void testinit() {
        //构造方法确实会产生精度问题
        BigDecimal a = new BigDecimal(0.1d);
        BigDecimal b = BigDecimal.valueOf(0.1d);
        System.out.println(a);
        System.out.println(b);
    }
    
    private final int DEF_DIV_SCALE = 10; //这个类不能实例化
    
    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    
    /**
     * 提供精确的减法运算。
     *
     * @return 两个参数的差
     */
    @Test
    public void sub() {
        BigDecimal b1 = new BigDecimal(Double.toString((double) 9));
        BigDecimal b2 = new BigDecimal(4);
        double v = b1.subtract(b2).doubleValue();
        System.out.println(v);
    }
    
    /**
     * 提供精确的乘法运算。
     *
     * @return 两个参数的积
     */
    @Test
    public void mul() {
        BigDecimal b1 = new BigDecimal(3);
        BigDecimal b2 = new BigDecimal(5);
        System.out.println(b1.multiply(b2).doubleValue());
    }
    
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }
    
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b2.divide(b1, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    @Test
    public void 除() {
        BigDecimal a = new BigDecimal(10);
        BigDecimal b = new BigDecimal(3);
        //没有第二个参数 无限循环数会报错
        System.out.println(a.divide(b, 2, RoundingMode.HALF_UP));
    }
    
    /**
     * setScale(2, RoundingMode.HALF_UP)原来的不变，会产生新的对象
     */
    @Test
    public void 保留2位小数() {
        a = new BigDecimal("1.323245454");
        System.out.println(a.doubleValue());
        //保留2位小数
        BigDecimal b = a.setScale(2, RoundingMode.HALF_UP);
        System.out.println(a);
        System.out.println(b);
        System.out.println(b.doubleValue());
    }
    
    /**
     * 格式化
     */
    @Test
    public void formatNumber() {
        //百分比
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        System.out.println(percent.format(a));
    }
}
