package com.xl.util;

import com.xl.base.IdWorker;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-04
 * @time 14:30
 * To change this template use File | Settings | File Templates.
 */
public class HttpUtil {
    public static void download(URL imgurl) throws IOException {
        URLConnection connection = imgurl.openConnection();
        IdWorker idWorker = new IdWorker();
        File parentFile = new File(FileUtil.getDesktopFile() + "/temp");
        File file = new File(parentFile, +idWorker.nextId() + ".jpg");
        FileUtil.write(file, connection.getInputStream());
    }
}
