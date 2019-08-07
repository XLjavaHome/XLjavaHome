package com.xl.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-04
 * @time 14:30
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class HttpUtil {
    /**
     *
     * @param element
     * @param dectory 目录
     * @return
     * @throws IOException
     */
    public static File downloadImge(Element element, File dectory) throws IOException {
        URL imgurl = new URL(element.attr("src"));
        String alt = element.attr("alt");
        BufferedImage bis = ImageIO.read(imgurl);
        String path = imgurl.getPath();
        String postfix = path.substring(path.lastIndexOf(".") + 1);
        File file = new File(dectory, alt + path.replace("/", "_"));
        if (!file.exists()) {
            ImageIO.write(bis, postfix, file);
            log.info(imgurl.toString());
        } else {
            log.info(imgurl.toString() + "文件已存在");
        }
        return file;
    }
}
