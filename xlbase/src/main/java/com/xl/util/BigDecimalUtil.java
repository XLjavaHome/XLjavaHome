package com.xl.util;

import com.sun.istack.internal.NotNull;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-02-26
 * @time 16:52
 * To change this template use File | Settings | File Templates.
 */
public class BigDecimalUtil {
    /**
     * 初始化bigDecimal
     *
     * @param bigDecimal
     * @return
     */
    @NotNull
    public static BigDecimal initBigDecimal(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            bigDecimal = new BigDecimal(0);
        }
        return bigDecimal;
    }
    
    /**
     * 初始化bigDecimal
     *
     * @param doubleValue
     * @return
     */
    @NotNull
    public static BigDecimal initBigDecimal(Double doubleValue) {
        if (doubleValue == null) {
            doubleValue = 0D;
        }
        return new BigDecimal(doubleValue);
    }
    
    /**
     * 获取map中的BigDecimal
     *
     * @param data
     * @param key
     * @return
     */
    public static BigDecimal getBigDecimal(Map data, String key) {
        Object o = data.get(key);
        if (o == null) {
            return new BigDecimal("0");
        }
        if (o instanceof BigDecimal) {
            return (BigDecimal) o;
        } else {
            return new BigDecimal(o.toString());
        }
    }
    
    /**
     * 将map中的值转为BigDecimal
     *
     * @param map
     * @param key
     * @return
     */
    public static BigDecimal initBigDecimal(Map<String, Object> map, String key) {
        Object o = map.get(key);
        if (o != null) {
            if (o instanceof BigDecimal) {
                return (BigDecimal) o;
            } else {
                return new BigDecimal(o.toString());
            }
        }
        return null;
    }
}
