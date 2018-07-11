package com.xl.util;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-21
 * @Time: 9:59
 * To change this template use File | Settings | File Templates.
 */
public class TomcatUtil {
    public static String getPath() {
        return System.getProperty("catalina.home");
    }
}
