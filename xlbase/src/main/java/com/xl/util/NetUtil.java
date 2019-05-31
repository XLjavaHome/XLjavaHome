package com.xl.util;

import com.xl.enumsupport.CharacterEnum;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class NetUtil {
    /**
     * 根据url返回其html文本
     *
     * @param url url地址
     * @return
     */
    public static String getContent(String url) {
        try {
            URL _url = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
            conn.setReadTimeout(20000); // 设置超时
            conn.setRequestMethod("GET"); // 默认是GET,可以不用设置
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                byte[] content = StreamTool.getBytes(is);
                String code = conn.getHeaderField("Content-Type");
                if (code.contains("GBK")) {
                    return new String(content, "GBK");
                } else {
                    return new String(content, CharacterEnum.UTF8.getValue()); // 默认以UTF-8编码
                }
            } else {
                throw new IllegalStateException("访问网络失败");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过url获取输入流
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static InputStream getInputStream(String url) {
        try {
            URL _url = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
            conn.setConnectTimeout(20000);
            // conn.setRequestMethod("get");//为了获取其他资源,用get请求会报异常
            int code = conn.getResponseCode();
            if (code == 200) {
                return conn.getInputStream();
            }
        } catch (Exception e) {
            throw new RuntimeException("获取url失败");
        }
        return null;
    }
}
