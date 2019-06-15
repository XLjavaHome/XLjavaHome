package com.xl.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 批量下载图像工具类
 *
 * @author arry
 * @version v1.0
 */
@Log4j
public class DownUtil {
    /**
     * 解析网页源代码
     *
     * @param url      网址
     * @param encoding 网页编码集
     * @return List<HashMap<String,String>> 列表集合
     * @author arry
     */
    public static List<HashMap<String, String>> findImages(String url, String encoding) {
        // 定义图像列表集合的容器
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        // 根据网址和页面的编码集，获取网页的源码
        String html = getHtmlResourceByUrl(url, encoding);
        // 解析网页源代码（找到图像标签）
        org.jsoup.nodes.Document document = Jsoup.parse(html);
        // 获取页面中所有的图片标签
        Elements elements = document.getElementsByTag("img");
        HashMap<String, String> map = null;
        for (Element element : elements) {
            // 根据图片标签的属性src的值来获取图片的网络地址<img src="" alt="" width="" height="" />
            String src = element.attr("src");
            if (src.startsWith("http") && src.indexOf("jpg") != -1) {
                map = new HashMap<String, String>();
                map.put("src", src);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 根据网址和页面的编码集，获取网页的源码
     *
     * @param url      网址
     * @param encoding 网页的编码集
     * @return String 网页的源代码
     * <br /><br />
     * <a href="https://www.baidu.com/" style="font-size:30px;color:red;">百度一下，你就知道 ！</a>
     * @author arry
     */
    public static String getHtmlResourceByUrl(String url, String encoding) {
        // 存储源代码的容器
        StringBuffer buffer = new StringBuffer();
        URL urlObj = null;
        URLConnection uc = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            // 建立网络连接
            urlObj = new URL(url);
            // 打开网络链接
            uc = urlObj.openConnection();
            // 建立文件流
            isr = new InputStreamReader(uc.getInputStream(), "gbk");
            // 建立文件缓冲流
            reader = new BufferedReader(isr);
            String temp = null; // 临时变量
            while ((temp = reader.readLine()) != null) {
                buffer.append(temp); // 一边读，一边写
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("世界上最遥远的距离就是没有网 ！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("您的网络连接失败，请稍后重试 ！");
        } finally {
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    public static void main(String[] arfsdfsdfdgs) {
        System.out.println("亲爱的同学们，大家晚上好，我爱你们 ！");
        String url = "http://tech.qq.com/";
        String encoding = "gbk";
        // 1. 根据网址和页面的编码集，获取网页的源码
        String html = getHtmlResourceByUrl(url, encoding);
        // 2. 解析网页源代码（找到图像标签）
        Document document = Jsoup.parse(html);
        // 获取页面中所有的图片标签
        Elements elements = document.getElementsByTag("img");
        // 定义图像列表集合的容器
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = null;
        for (Element element : elements) {
            // 根据图片标签的属性src的值来获取图片的网络地址<img src="" alt="" width="" height="" />
            String src = element.attr("src");
            if (src.startsWith("http") && src.indexOf("jpg") != -1) {
                map = new HashMap<String, String>();
                map.put("src", src);
                list.add(map);
            }
        }
        System.out.println(list);
        // 3. 组装数据，同步到数据库中，将图像下载到服务器中
        // 4. 返回到页面中进行展示
    }
}
