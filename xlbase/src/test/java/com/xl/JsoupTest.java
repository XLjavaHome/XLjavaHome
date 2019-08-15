package com.xl;

import com.xl.util.FileUtil;
import com.xl.util.JSoupUtil;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-03
 * @time 22:25
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class JsoupTest {
    /**
     * 下载一个网页的图片
     *
     * @throws IOException
     */
    @Test
    void downloadImage() throws IOException {
        File parentFile = new File(FileUtil.getDesktopFile() + "/temp");
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        String baseUri = "https://www.mzitu.com/118183/15";
        Document document = Jsoup.connect(baseUri).get();
        JSoupUtil.downLoadImg(parentFile, document);
    }
    
    @Test
    void downloadImagesInBulk() throws IOException {
        //1.创建目录
        File parentFile = new File(FileUtil.getDesktopFile() + "/temp");
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        //2.解析该页面的所有图片
        //3.获取页面的所有a标签
        //打开a标签的网页，下载所有图片
        //        打开该页面的所有a标签
        Document document = Jsoup.connect("https://www.meituri.com/").userAgent("Mozilla").get();
        Elements aTag = document.getElementsByTag("a");
        aTag.parallelStream().forEach(element -> {
            String href = element.attr("href");
            try {
                Document aUrl = Jsoup.connect(href).get();
                JSoupUtil.downLoadImg(parentFile, aUrl);
            } catch (IOException e) {
                log.error("图片下载失败{}", href);
            }
        });
    }
    
    
    @Test
    void name2() throws IOException {
        Document doc = Jsoup.connect("http://www.baidu.com/").data("query", "Java").userAgent("Mozilla").cookie("auth", "token")
                            .timeout(3000).get();
        doc.getAllElements().forEach(System.out::println);
    }
    
    @Test
    void demo1() throws IOException {
        Set<String> setUrls = IntStream.rangeClosed(1, 5).mapToObj(
                i -> "https://www.oschina.net/project/list?company=0&sort=score&lang=0&recommend=false&p=" + i)
                                       .collect(Collectors.toSet());
        Set<String> setProjUrls = new HashSet<>();
        for (String stringUrl : setUrls) {
            Document document = Jsoup.connect(stringUrl)
                                     .userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();
            //  System.out.println(document);
            Elements elements = document.select("div.box.item");
            for (Element element : elements) {
                Elements eleUrl = element.select("div.box-aw a");
                String strPrjUrl = eleUrl.attr("href");
                setProjUrls.add(strPrjUrl);
                //  System.out.println(strPrjUrl);
                Elements eleTitle = eleUrl.select(".title");
                String strTitle = eleTitle.text();
                // System.out.println(strTitle);
                Elements eleSummary = eleUrl.select(".summary");
                String strSummary = eleSummary.text();
                //  System.out.println(strSummary);
            }
        }
        for (String stringUrl : setProjUrls) {
            Document document = Jsoup.connect(stringUrl)
                                     .userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();
            Elements elements = document.select("div.box-aw a h1");
            String strTitle = elements.text();
            System.out.println("标题：" + strTitle);
            Elements elementsSection = document.select("section.list");
            int nSize = elementsSection.get(0).children().size();
            if (nSize == 0)
                continue;
            Element elementProtocol = elementsSection.get(0).child(0);
            Elements elesPro = elementProtocol.select("span");
            String strPro = elesPro.text();
            System.out.println("开源协议：" + strPro);
            nSize--;
            if (nSize == 0)
                continue;
            Element elementLan = elementsSection.get(0).child(1);
            Elements elesLan = elementLan.select("span").get(0).children();
            StringBuilder strlan = new StringBuilder();
            for (Element ele : elesLan) {
                String strLanTemp = ele.text();
                if (strLanTemp.indexOf("查看源码") >= 0)
                    break;
                strlan.append(strLanTemp + ",");
            }
            if (elesLan.size() > 0) {
                String strLanguage = strlan.toString().substring(0, strlan.length() - 1);
                System.out.println("开发语言：" + strLanguage);
            }
            nSize--;
            if (nSize == 0)
                continue;
            Element elementOS = elementsSection.get(0).child(2);
            Elements elesOS = elementOS.select("span");
            String strOS = elesOS.text();
            System.out.println("操作系统：" + strOS);
            nSize--;
            if (nSize == 0)
                continue;
            Element elementAuthor = elementsSection.get(0).child(3);
            Elements elesAuthor = elementAuthor.select("a.link");
            String strAuthor = elesAuthor.text();
            System.out.println("软件作者；" + strAuthor);
            System.out.println("---------------------");
        }
    }
}
