package com.xl.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * @Date: 2018-05-09
 * @Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public class BrowserUtil {
    /**
     * 浏览器打开URL
     *
     * @param s
     * @throws IOException
     */
    public static void open(String s) throws IOException {
        Desktop.getDesktop().browse(URI.create(s));
    }
    
    public static Process openWithChrome(String baseUri) throws IOException {
        return Runtime.getRuntime()
                      .exec(new String[]{"C:\\Users\\Lenovo\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe", baseUri});
    }
}
