package com.xl.util;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * @Date: 2018-05-16
 * @Time: 17:49
 * To change this template use File | Settings | File Templates.
 */
public class ResourceUtil {
    /**
     * 获取资源目录下的输入流,不能以"/"开头
     *
     * @param path
     * @return
     */
    public static InputStream getResourceInputStream(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }
    
    /**
     * "/"开头
     *
     * @param path
     * @return
     */
    public static InputStream getResourceInputStream2(String path) {
        return ResourceUtil.class.getResourceAsStream(path);
    }
    
    /**
     * 不能以"/"开头
     *
     * @param path
     * @return
     */
    public static InputStream getResourceInputStream3(String path) {
        return ResourceUtil.class.getClassLoader().getResourceAsStream(path);
    }
}
