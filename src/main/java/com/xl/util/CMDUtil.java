package com.xl.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017/10/11
 * Time: 13:25
 * To change this template use File | Settings | File Templates.
 */
public class CMDUtil {
    /**
     * 关闭java进程
     *
     * @throws IOException
     */
    public static void closeJava() throws IOException {
        Runtime.getRuntime().exec("taskkill /f /t /im java.exe");
    }
    
    public static List<String> getCMDINfo(String cmd) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(cmd);
        //UTF-8乱码，不加字符集也乱码，GBK就好了
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
        ArrayList<String> list = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (StringUtil.isNotEmpty(line)) {
                list.add(line);
            }
        }
        process.waitFor();
        return list;
    }
}
