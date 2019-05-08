package com.xl.util;

import lombok.extern.log4j.Log4j;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * Date: 2017-11-20
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class Print {
    /**
     * 输出
     *
     * @param obj
     */
    public static void print(Object obj) {
        System.out.println(obj);
    }

    /**
     * 输出
     *
     * @param obj
     */
    public static void info(Object obj) {
        System.out.println(obj);
    }
}
