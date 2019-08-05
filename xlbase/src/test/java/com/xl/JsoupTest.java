package com.xl;

import com.xl.base.IdWorker;
import com.xl.util.FileUtil;
import com.xl.util.HttpUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
public class JsoupTest {
    @Test
    void down() throws IOException {
        // TODO 2019/8/5 23:34 徐立 图片下载
        Document document = Jsoup.connect("https://www.meituri.com/").get();
        document = Jsoup.connect("https://www.meituri.com/a/27581/").get();
        Elements img = document.getElementsByTag("img");
        //用并行流文件名一致导致图片没有保存
        img.stream().filter(element -> element.attr("src").endsWith(".jpg")).forEachOrdered(element -> {
            String src = element.attr("src");
            try {
                IdWorker idWorker = new IdWorker();
                File parentFile = new File(FileUtil.getDesktopFile() + "/temp1");
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                File file = new File(parentFile, +idWorker.nextId() + ".jpg");
                file = HttpUtil.downloadImge(new URL(src), file);
                System.out.println(file.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    
    @Test
    void name() throws IOException {
        Document doc = Jsoup.connect("https://ii.hywly.com/a/1/1076/42.jpg").get();
        String title = doc.title();
        System.out.println(title);
    }
    
    @Test
    void dowmloadImage() throws IOException {
        Document doc = Jsoup.connect("http://www.csdn.net/").get();
        doc = Jsoup.connect("https://ii.hywly.com/a/1/1076/42.jpg").get();
        Elements img = doc.getElementsByTag("img");
        File parentFile = new File(FileUtil.getDesktopFile() + "/temp");
        img.stream().filter(element -> {
            String src = element.attr("src");
            return src.endsWith(".png") || src.endsWith("jpg");
        }).forEach(element -> {
            String src = element.attr("src");
            try {
                HttpUtil.downloadImge(new URL(src));
                System.out.println(src);
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        });
        FileUtil.open(parentFile);
    }
    
    public final byte[] 输入流转字节数组(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
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
