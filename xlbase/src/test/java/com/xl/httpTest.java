package com.xl;

import http.HttpRequestUtil;
import java.io.IOException;
import java.net.URL;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-09
 * @time 23:48
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class httpTest {
    @Test
    public void name() {
    }
    
    @Test
    public void tes1() throws IOException {
        String spec;
        spec =
                "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&ch=9&tn=98012088_6_dg&wd=%E9%A6%96%E9%A1%B5&oq=1&rsv_pq=bb96c8ab00723117&rsv_t=910531mds6Lf8RbX4uaqS4%2BLHa03AVnkFT8%2FS22mXHpyPeRLkgqjCSljiiDjO3eUA7eVPA&rqlang=cn&rsv_enter=1&rsv_sug3=8&rsv_sug1=7&rsv_sug7=100&rsv_sug2=0&inputT=4882&rsv_sug4=5563";
        StringBuffer sb = HttpRequestUtil.httpRequest(new URL(spec));
        System.out.println(sb);
    }
}
