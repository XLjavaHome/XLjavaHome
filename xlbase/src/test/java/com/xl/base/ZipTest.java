package com.xl.base;

import com.xl.util.FileUtil;
import com.xl.util.ZipUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-23
 * @time 9:58
 * To change this template use File | Settings | File Templates.
 */
public class ZipTest {
    @Test
    public void name() throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("jar/desktop.zip");
        File file = new File(resource.getFile());
        ZipUtils.uncompression(file, FileUtil.getTempDrectory().getAbsolutePath());
    }
}
