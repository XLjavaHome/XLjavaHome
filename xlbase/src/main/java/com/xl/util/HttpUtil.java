package com.xl.util;

import com.xl.base.IdWorker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-04
 * @time 14:30
 * To change this template use File | Settings | File Templates.
 */
public class HttpUtil {
    public static File downloadImge(URL imgurl) throws IOException {
        IdWorker idWorker = new IdWorker();
        File parentFile = new File(FileUtil.getDesktopFile() + "/temp");
        File file = new File(parentFile, +idWorker.nextId() + ".png");
        return downloadImge(imgurl, file);
    }
    
    public static File downloadImge(URL imgurl, File file) throws IOException {
        BufferedImage image = ImageIO.read(imgurl);
/*        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        bufferedImage.getGraphics().drawImage(image, 0, 0, null);*/
        ImageIO.write(image, "png", file);
        return file;
    }
}
