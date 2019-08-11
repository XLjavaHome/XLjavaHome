package com.xl.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
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
    public static File downloadImge(Element element, File dectory) throws IOException, InterruptedException {
        URL imgurl = new URL(element.attr("src"));
        String alt = element.attr("alt");
        BufferedImage bis = ImageIO.read(imgurl);
        String path = imgurl.getPath();
        String postfix = path.substring(path.lastIndexOf(".") + 1);
        File file = new File(dectory, alt + path.replace("/", "_"));
        if (!file.exists()) {
            //原始图像的宽度和高度
            int width = bis.getWidth();
            int height = bis.getHeight();
            //调整后的图片的宽度和高度
            float v = 1800 / width;
            if (v < 2) {
                v = 4;
            }
            log.info(String.valueOf(v));
            int toWidth = (int) (width * v);
            int toHeight = (int) (height * v);
            /* 新生成结果图片 */
            BufferedImage result111 = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
            result111.getGraphics().drawImage(bis.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
            BufferedImage result11 = result111;
            ImageIO.write(result11, postfix, file);
            Thread.sleep(1000);
            log.info(imgurl.toString());
        } else {
            log.info(imgurl.toString() + "文件已存在");
        }
        return file;
    }
    
    public static boolean writeHighQuality(BufferedImage im, File file) {
        try {
            /*输出到文件流*/
            FileOutputStream newimage = new FileOutputStream(file);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(im);
            /* 压缩质量 */
            jep.setQuality(0.9f, true);
            encoder.encode(im, jep);
            /*近JPEG编码*/
            newimage.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
