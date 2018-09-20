package com.xl.html;

import com.xl.util.Print;
import com.xl.util.RegTool;
import com.xl.util.ResourceUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import org.dom4j.DocumentException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 徐立
 * @Decription 利用Jsoup解析html
 * @date 2014年3月19日
 */
public class HtmlTest {
    org.jsoup.nodes.Document doc;
    /**
     * 要解析的html
     */
    private InputStream file;
    private URL url;
    private int timeout = 5000;

    @Before
    public void init() throws IOException {
        file = ResourceUtil.getResourceInputStream("html/1.html");
        //doc = Jsoup.parse(file, CharsetEnum.UTF8.getValue());
        url = new URL("http://www.baidu.com/baidu?word=%E9%A3%8E%E6%99%AF&ie=utf-8&tn=98012088_2_dg&1402964659639");
        url = new URL("http://bbs.goumin.com/thread-2939853-1-1.html");
    }

    /**
     * 大写小写都可以
     *
     * @throws DocumentException
     * @throws IOException
     */
    @Test
    public void read() throws DocumentException, IOException {
        Elements content = doc.getElementsByTag("title");
        Print.print(content.html());
        content = doc.getElementsByTag("TITLE");
        System.out.println(content);
        Elements body = doc.getElementsByTag("body");
        System.out.println(body.html().replaceAll("[\\s\\r\\n]+", " "));
    }

    @Test
    public void internet() throws IOException {
        doc = Jsoup.parse(url, timeout);
        Print.print(doc);
        Elements e = doc.getElementsByTag("a");
        String content = e.toString().replaceAll("\r", " ");
        Print.print(content);
        String[] str = RegTool.getContent(content, "home.php.*?_blank.*?>(.*?)<", 1);
        System.out.println(Arrays.toString(str));
        while (e.hasText()) {
            Print.print(e.attr("href"));
        }
    }

    @Test
    public void a() {
        Elements a = doc.getElementsByTag("a");
        for (Element e : a) {
            System.out.println(e);
            System.out.println(e.attr("href"));
        }
    }
}
