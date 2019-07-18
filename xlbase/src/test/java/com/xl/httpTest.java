package com.xl;

import http.HttpRequestUtil;
import java.io.IOException;
import java.net.URL;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-09
 * @time 23:48
 * To change this template use File | Settings | File Templates.
 */
public class httpTest {
    @Test
    public void name() {
    }
    
    @Test
    public void tes1() throws IOException {
        String spec = "http://localhost:8080/";
        spec = "http://localhost:8080/#/workflow/example/manager-task-list";
        StringBuffer sb = HttpRequestUtil.httpRequest(new URL(spec));
        System.out.println(sb);
    }
}
