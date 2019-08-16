package com.xl.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author 徐立
 * @Decription 系统工具类
 * @date 2013-12-20
 */
public class SystemUtil {
    public static InputStream exec(String[] command) {
        try {
            Process p = Runtime.getRuntime().exec(command);
            return p.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("执行命令失败");
        }
    }
    
    /**
     * 得到控制台打印的信息
     *
     * @param command
     * @return String
     */
    public static String getShellInfo(String command) throws IOException {
        return IOUtil.getContent(exec(command));
    }
    
    /**
     * 执行系统命令
     *
     * @param command
     * @return InputStream
     */
    public static InputStream exec(String command) {
        try {
            Process p = Runtime.getRuntime().exec(command);
            return p.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("执行命令失败");
        }
    }
    
    public static void open(File file) throws IOException {
        Desktop d = Desktop.getDesktop();
        d.open(file);
    }
    
    /**
     * 获取系统的默认编码
     *
     * @return
     */
    public static Charset getDefaultEncoding() {
        return Charset.defaultCharset();
    }
}
