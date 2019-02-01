package com.xl.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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
    final Map<String, String> params = new HashMap<>();
    
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
    
    /**
     * 测试
     */
    @Test
    public void demo2Test() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String baseUrl = "http://translate.google.cn/translate_a/single";
        CloseableHttpResponse response = null;
        try {
            response = send(httpClient, baseUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    protected CloseableHttpResponse send(CloseableHttpClient httpClient, String base) throws Exception {
        List<NameValuePair> formParams = new ArrayList<>();
        for (String key : params.keySet()) {
            String value = params.get(key);
            formParams.add(new BasicNameValuePair(key, value));
        }
        HttpPost request = new HttpPost(base);
        RequestConfig localConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        request.setConfig(localConfig);
        request.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");        //内容为post
        return httpClient.execute(request);
    }
}

