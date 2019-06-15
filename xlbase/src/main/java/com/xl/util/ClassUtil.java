package com.xl.util;

/**
 * Created with 徐立. 类的工具类
 *
 * @author 徐立
 * @date 2019-06-15
 * @time 23:42
 * To change this template use File | Settings | File Templates.
 */
public class ClassUtil {
    /**
     * 获取类的全名,可以用来判断类是否存在
     *
     * @param clazz
     * @return
     */
    public static String getFullClass(Class<?> clazz) {
        return clazz.getName().replace(".", "/") + ".class";
    }
}
