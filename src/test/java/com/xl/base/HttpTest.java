package com.xl.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-06-11
 * @Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
public class HttpTest {
    @Test
    public void demoTest() throws IOException {
        HttpURLConnection conn = null;
        URL realUrl = new URL("http://www.hao123.com");
        conn = (HttpURLConnection) realUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setUseCaches(false);
        conn.setReadTimeout(8000);
        conn.setConnectTimeout(8000);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");
        int code = conn.getResponseCode();
        if (code == 200) {
            InputStream is = conn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            String result = buffer.toString();
            //subscriber是观察者，在本代码中可以理解成发送数据给activity
        }
    }
}
