package com.xl.util;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * @Date: 2018-05-08
 * @Time: 9:20
 * To change this template use File | Settings | File Templates.
 */
public class MethodUtil {
    public static String getMethodName() {
        return "这是方法名称：" + getMethodName2();
    }

    /**
     * 获取这个方法的名称:效率要高
     *
     * @return
     */
    public static String getMethodName2() {
        return new Throwable().getStackTrace()[1].getMethodName();
    }

    /**
     * 获取这个方法的名称
     *
     * @return
     */
    public static String getMethodName3() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
}
