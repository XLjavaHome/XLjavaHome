package com.xl.util;

import com.xl.enumsupport.CharsetEnum;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.antlr.v4.runtime.misc.NotNull;

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

    /**
     * 获取资源文件的输入流
     *
     * @param path
     * @return
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     */
    public static FileInputStream getResourceFileInputStream(String path) throws UnsupportedEncodingException, FileNotFoundException {
        return new FileInputStream(getResourceFile(path));
    }

    /**
     * 获取资源目录下的文件
     *
     * @param path
     * @return
     */
    public static File getResourceFile(@NotNull String path) throws UnsupportedEncodingException {
        return new File(URLDecoder.decode(Thread.currentThread().getContextClassLoader().getResource(path).getFile(), CharsetEnum.UTF8.getValue()));
    }

    public static File createResourceFile(String path) throws IOException {
        File f = new File(getResourceFile(""), path);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        return f;
    }
}
