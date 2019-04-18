package com.xl.base;

import java.awt.Desktop;
import java.io.*;
import java.net.*;
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
    
    /**
     * 调用http接口简单例子
     */
    @Test
    public void interfaceTest() throws IOException {
        //请求的webservice的url
        URL url = new URL("http://www.longwenyun.com/lwSystem/user/getUserInfo.action");
        //创建http链接
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        //设置请求的方法类型
        httpURLConnection.setRequestMethod("POST");
        //设置请求的内容类型
        httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        //设置发送数据
        httpURLConnection.setDoOutput(true);
        //设置接受数据
        httpURLConnection.setDoInput(true);
        //发送数据,使用输出流
        OutputStream outputStream = httpURLConnection.getOutputStream();
        //发送的soap协议的数据
        String content = "user_id=" + URLEncoder.encode("13846", "gbk");
        //发送数据
        outputStream.write(content.getBytes());
        //接收数据
        InputStream inputStream = httpURLConnection.getInputStream();
        //定义字节数组
        byte[] b = new byte[1024];
        //定义一个输出流存储接收到的数据
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //开始接收数据
        int len = 0;
        while (true) {
            len = inputStream.read(b);
            if (len == -1) {
                //数据读完
                break;
            }
            byteArrayOutputStream.write(b, 0, len);
        }
        //从输出流中获取读取到数据(服务端返回的)
        String response = byteArrayOutputStream.toString();
        System.out.println(response);
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
    
    @Test
    public void 网页抓取() throws Exception {
        String urlString = "http://www.hao123.com";
        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
         InputStream is2 = urlConnection.getInputStream();
        InputStream is = url.openStream();
        InputStreamReader isr = new InputStreamReader(is, "utf-8");
        //为字符输入流添加缓冲
        BufferedReader br = new BufferedReader(isr);
        String data = br.readLine();//读取数据
        while (data != null) {//循环读取数据
            System.out.println(data);//输出数据
            data = br.readLine();
        }
        br.close();
        isr.close();
        is.close();
        Desktop.getDesktop().browse(URI.create(urlString));
    }
}

