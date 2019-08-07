package com.xl.util;

import java.io.File;
import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-07
 * @time 21:14
 * To change this template use File | Settings | File Templates.
 */
public class JSoupUtil {
    /**
     * 下载图片
     *
     * @param parentFile
     * @param document
     */
    public static void downLoadImg(File parentFile, Document document) {
        //获取页面所有图片
        Elements img = document.getElementsByTag("img");
        //用并行流
        img.parallelStream().filter(element -> element.attr("src").endsWith(".jpg")).forEach(element -> {
            try {
                Thread.sleep(1000);
                HttpUtil.downloadImge(element, parentFile);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
