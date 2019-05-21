package com.xl.base;

import java.net.URL;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-21
 * @time 13:44
 * To change this template use File | Settings | File Templates.
 */
public class jarFileTest {
    @Test
    public void 能否读取jar中的文件() {
        URL url = Thread.currentThread().getContextClassLoader().getResource("jar\\desktop.zip");
        System.out.println(url);
        System.out.println(url.getFile());
    }
}
