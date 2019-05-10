package com.xl.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

/**
 * File Desc:  数字工具类
 * Product Name: SIRM
 * Module Name: Common
 * Author:      潘多强
 * History:     2011-03-13 created by 潘多强
 */
public class NumberTool {
    public static final int YI = 100000000;//一亿
    public static final int WAN = 10000;//一万
    private static final int FOURDECIMALMEDIAN = 4;  //小数位数
    
    /**
     * 将object转换成整型，当传入的对象是null时返回指定的值
     *
     * @param o     数值
     * @param dv    默认值
     * @param round 有效小数位数
     * @return
     */
    public static Double safeToDouble(Object o, Double dv, int round) {
        Double r = dv;
        if (o != null) {
            try {
                r = new Double(String.valueOf(o));
                if (r.isNaN()) {
                    return dv;
                }
            } catch (Exception ex) {
            }
        }
        r = new BigDecimal(r).setScale(round, BigDecimal.ROUND_HALF_UP).doubleValue();
        return r;
    }
    
    /**
     * 将object转换成整型，当传入的对象是null时返回指定的值
     *
     * @param o
     * @param dv
     * @return
     */
    public static Float safeToFloat(Object o, Float dv) {
        Float r = dv;
        if (o != null) {
            try {
                r = new Float(String.valueOf(o));
                if (r.isNaN()) {
                    return dv;
                }
            } catch (Exception ex) {
            }
        }
        return r;
    }
    
    /**
     * 将object转换成float，当传入的对象是null时返回指定的值
     *
     * @param o     数值
     * @param dv    默认值
     * @param round 有效小数位数
     */
    public static Float safeToFloat(Object o, Float dv, int round) {
        Float r = dv;
        if (o != null) {
            try {
                r = new Float(String.valueOf(o));
                if (r.isNaN()) {
                    return dv;
                }
            } catch (Exception ex) {
            }
        }
        r = new BigDecimal(r).setScale(round, BigDecimal.ROUND_HALF_UP).floatValue();
        return r;
    }
    
    /**
     * String类型 转 BigDecimal类型
     *
     * @return paraValue
     */
    public static BigDecimal stringToBigDecimal(String paraValue) {
        try {
            BigDecimal bDecimal = null;
            if (paraValue.indexOf("%") == -1) {
                return new BigDecimal(Double.valueOf(paraValue.trim().replace(",", ""))).setScale(4, BigDecimal.ROUND_HALF_UP);
            } else {
                return new BigDecimal(Double.valueOf(paraValue.trim().replace("%", "")) / 100)
                        .setScale(FOURDECIMALMEDIAN, BigDecimal.ROUND_HALF_UP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * String类型 转 BigDecimal类型
     *
     * @return paraValue
     */
    public static BigDecimal stringToBigDecimalTwoSpilt(String paraValue) {
        try {
            BigDecimal bDecimal = null;
            if (paraValue.indexOf("%") == -1) {
                return new BigDecimal(Double.valueOf(paraValue.trim().replace(",", ""))).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else {
                return new BigDecimal(Double.valueOf(paraValue.trim().replace("%", "")) / 100)
                        .setScale(FOURDECIMALMEDIAN, BigDecimal.ROUND_HALF_UP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * aParaValue值 - bParaValue值
     *
     * @return BigDecimal
     */
    public static BigDecimal aSubtractB(BigDecimal aParaValue, BigDecimal bParaValue) {
        try {
            return aParaValue.subtract(bParaValue).setScale(FOURDECIMALMEDIAN, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * aParaValue值 / bParaValue值
     *
     * @return BigDecimal
     */
    public static BigDecimal aDivideB(BigDecimal aParaValue, BigDecimal bParaValue) {
        try {
            return aParaValue.divide(bParaValue, FOURDECIMALMEDIAN).setScale(FOURDECIMALMEDIAN, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static double avg(double[] sequenceArr) {
        if (sequenceArr != null && sequenceArr.length > 0) {
            double sum = 0.00;
            int T = sequenceArr.length;
            for (int i = 0; i < T; i++) {
                sum += sequenceArr[i];
            }
            return sum / T;
        }
        return 0.00;
    }
    
    public static double round(double value, int count) {
        int t = (int) Math.pow(10.0, (double) (count + 1));
        return ((double) Math.round(value * t)) / t;
    }
    
    public static Double getFourDecimalNumber(Number number) {
        if (number == null || (0.00 == number.doubleValue())) {
            return 0.0000;
        }
        NumberFormat format = new DecimalFormat("##0.0000");
        return Double.valueOf(format.format(number));
    }
    
    /**
     * 从指定Map中获取指定Key的值，并将期值转为Integer型，若Map中指定Key不存在，则直接返回0
     *
     * @param map Map
     * @param key Key
     * @return 将Map值转换后的Integer值，若不存在则返回0
     */
    public static Integer convertMapKeyToInt(Map map, String key) {
        return convertMapKeyToInt(map, key, 0);
    }
    
    /**
     * 从指定Map中获取指定Key的 值，并将期值转为Integer型，若Map中指定Key不存在，则直接返回默认值
     *
     * @param map          Map
     * @param key          Key
     * @param defaultValue 默认值
     * @return 将Map值转换后的Integer值，若不存在则返回默认值
     */
    public static Integer convertMapKeyToInt(Map map, String key, Integer defaultValue) {
        return safeToInteger(map.get(key), defaultValue);
    }
    
    /**
     * 将object转换成整型，当传入的对象是null时返回指定的值
     *
     * @param o
     * @param dv
     * @return
     */
    public static Integer safeToInteger(Object o, Integer dv) {
        Integer r = dv;
        if (o != null) {
            try {
                r = new Integer(String.valueOf(o));
            } catch (Exception ex) {
            }
        }
        return r;
    }
    
    /**
     * 将int数组转换成以逗号分隔的字符串
     *
     * @param intArray
     * @return
     */
    public static String convertIntArrayToString(int[] intArray) {
        if (intArray.length <= 0) {
            return "";
        }
        String _string = "";
        for (int _int : intArray) {
            _string += "".equals(_string) ? new Integer(_int).toString() : "," + new Integer(_int).toString();
        }
        return _string;
    }
    
    /**
     * 提供精确的加法运算。
     *
     * @param v1
     * @param v2
     * @return 两个参数的和
     */
    public static Double safeAdd(Double v1, Double v2) {
        if (v1 != null && v2 != null) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.add(b2).doubleValue();
        }
        return null;
    }
    
    /**
     * 提供精确的减法运算。
     *
     * @param v1 减数
     * @param v2 被减数
     * @return 两个参数的差
     */
    public static Double safeSub(Double v1, Double v2) {
        if (v1 != null && v2 != null) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.subtract(b2).doubleValue();
        }
        return null;
    }
    
    /**
     * 提供精确的除法运算,使用默认精确度。
     *
     * @param v1 除数
     * @param v2 被除数
     * @return 两个参数相除结果
     */
    public static Double safeDiv(Double v1, Double v2) {
        return safeDiv(v1, v2, FOURDECIMALMEDIAN);
    }
    
    /**
     * 提供精确的除法运算。
     *
     * @param v1    除数
     * @param v2    被除数
     * @param sacle 精确度
     * @return 两个参数相除结果
     */
    public static Double safeDiv(Double v1, Double v2, int sacle) {
        if (v1 != null && v2 != null && v2 != 0.0) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.divide(b2, sacle, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return null;
    }
    
    /**
     * 提供精确的乘法运算。
     *
     * @param v1
     * @param v2
     * @return 两个参数的积
     */
    public static Double safeMul(Double v1, Double v2) {
        if (v1 != null && v2 != null) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.multiply(b2).doubleValue();
        }
        return null;
    }
    
    /**
     * 格式化数字
     *
     * @param number  数字
     * @param pattern 格式
     * @return
     */
    public static String formateNumber(double number, String pattern) {
        if (number == 0) {
            return "0";
        }
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(number);
    }
    
    public static String formatNumber(String number, String pattern) {
        if (StringUtil.isEmpty(number)) {
            return "";
        }
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(safeToDouble(number, 0D));
    }
    
    /**
     * 将object转换成整型，当传入的对象是null时返回指定的值
     *
     * @param o
     * @param dv
     * @return
     */
    public static Double safeToDouble(Object o, Double dv) {
        Double r = dv;
        if (o != null) {
            try {
                r = new Double(String.valueOf(o));
                if (r.isNaN()) {
                    return dv;
                }
            } catch (Exception ex) {
            }
        }
        return r;
    }
    
    /**
     * 两个数是否相等
     *
     * @param s
     * @param i
     * @return
     */
    public static boolean equals(Number s, Number i) {
        if (s != null && i != null) {
            return s.doubleValue() == i.doubleValue();
        }
        return false;
    }
    
    /**
     * 数组中相加
     *
     * @param <T>
     * @param numbers
     * @return
     */
    public static <T extends Number> double safeToAdd(T[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        BigDecimal a = new BigDecimal(numbers[0].doubleValue());
        for (int i = 1; i < numbers.length; i++) {
            a = a.add(new BigDecimal(numbers[i].doubleValue()));
        }
        return a.doubleValue();
    }
}

