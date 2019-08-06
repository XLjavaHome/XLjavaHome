package com.xl.util;

import com.xl.base.IdWorker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import lombok.extern.log4j.Log4j;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-04
 * @time 14:30
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class HttpUtil {
    public static File downloadImge(URL imgurl) throws IOException {
        IdWorker idWorker = new IdWorker();
        File parentFile = new File(FileUtil.getDesktopFile() + "/temp");
        File file = new File(parentFile, +idWorker.nextId() + ".png");
        return downloadImge(imgurl, file);
    }
    
    /**
     * @param imgurl
     * @param dectory 目录
     * @return
     * @throws IOException
     */
    public static File downloadImge(URL imgurl, File dectory) throws IOException {
        BufferedImage image = ImageIO.read(imgurl);
        String path = imgurl.getPath();
        String postfix = path.substring(path.lastIndexOf(".") + 1);
        //postfix = "png";
        File file = new File(dectory, path.replace("/", "").replace("jpg", "png"));
        if (!file.exists()) {
            ImageIO.write(image, postfix, file);
            log.info(path + "文件已存在");
        } else {
            log.info(path);
        }
        return file;
    }
}
