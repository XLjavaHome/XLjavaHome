package com.xl.util;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
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
        //方式一
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        //方式二
        //return FileUtil.class.getClassLoader().getResourceAsStream(path);
    }
}
