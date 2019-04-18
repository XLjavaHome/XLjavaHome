package com.xl.url;

import com.xl.util.FileUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLTest {
    @Test
    public void demoTest() throws IOException {
        URL cnblogs = new URL("http://www.cnblogs.com/");
        BufferedReader reader = new BufferedReader(new InputStreamReader(cnblogs.openStream()));
    }

    @Test
    public void testUrl() throws Exception {
        String s = "http://www.hao123.com.com/juruboba/";
        URL url = new URL(s);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(5000); // 5秒获取不到就超时
        System.out.println(url.getProtocol() + url.getHost());
        conn.setRequestProperty("if-modifyed-since", System.currentTimeMillis() + ""); //  还不清楚这是做啥的
        System.out.println(conn.getHeaderFields());
        // HTTPURL拥有更多的方法
        int code = conn.getResponseCode();// 200为返回成功,404找不到页面,500服务器出错
        System.out.println("response状态码" + code);
        System.out.println("请求方式:" + conn.getRequestMethod());
        System.out.println("编码方式:" + conn.getHeaderField("Content-Type"));
        System.out.println(conn.getURL());
    }

    @Test
    public void testGetURL() throws IOException {
        URL u = getURL(FileUtil.class, "/");
        System.out.println(u); // file:/D:/mywork/javaSE/bin/util/1
        System.out.println(u.getContent()); // 如果有资源java.io.BufferedInputStream@983d95
        System.out.println(u.getDefaultPort()); // -1
        System.out.println(u.getPath()); // /D:/mywork/javaSE/bin/util/1
        System.out.println(u.getFile()); // /D:/mywork/javaSE/bin/util/1
        File file = new File(u.getFile());
        System.out.println(file.getAbsolutePath());// D:\mywork\javaSE\bin\\util
        File parent = file.getParentFile().getParentFile();
        System.out.println(parent); // 当前工程的绝对路径D:\mywork\javaSE
    }

    /**
     * 获取该类目录下资源文件的URL
     *
     * @param clazz    类
     * @param resource 1.null或""或/:返回不包括自己得URL<br/>
     *                 2.:当前classpath的绝对路径
     * @return URL .class文件对应的绝对路径
     */
    public static URL getURL(Class clazz, String resource) {
        if (resource == null) {
            resource = "";
        }
        return clazz.getResource(resource);
    }
}
