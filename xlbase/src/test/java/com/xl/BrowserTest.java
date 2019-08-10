package com.xl;

import com.xl.util.BrowserUtil;
import java.io.IOException;
import org.testng.annotations.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-10
 * @time 14:42
 * To change this template use File | Settings | File Templates.
 */
public class BrowserTest {
    private String url = "https://www.baidu.com/";
    
    @Test
    public void testOpen() throws IOException {
        BrowserUtil.open(url);
    }
    
    @Test
    public void testOpenWithChrome() throws IOException {
        BrowserUtil.openWithChrome(url);
    }
}
