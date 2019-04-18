package com.xl.internet;

import com.xl.enumsupport.CharsetEnum;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.junit.Test;

public class URLDemo {
    @SuppressWarnings("deprecation")
    @Test
    public void test() throws UnsupportedEncodingException {
        String s = "dse中国sdf";
        //encode(s) 默认采取项目编码，这个项目是utf-8
        String _s = java.net.URLEncoder.encode(s, CharsetEnum.UTF8.getValue());
        System.out.println(_s);
        _s = java.net.URLEncoder.encode(s, CharsetEnum.UTF8.getValue());
        System.out.println(_s);
        s = URLDecoder.decode(_s);
        System.out.println(s);
        s = URLDecoder.decode(_s, CharsetEnum.UTF8.getValue());
        System.out.println(s);
        s = URLDecoder.decode(_s, CharsetEnum.GBK.getValue());
        System.out.println(s);
    }
}
