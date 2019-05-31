package com.xl.internet;

import com.xl.enumsupport.CharacterEnum;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.junit.Test;

public class URLTest {
    private String s = "dse中国sdf";
    
    @SuppressWarnings("deprecation")
    @Test
    public void test() throws UnsupportedEncodingException {
        //encode(s) 默认采取项目编码，这个项目是utf-8
        String _s = java.net.URLEncoder.encode(s, CharacterEnum.UTF8.getValue());
        System.out.println(_s);
        _s = java.net.URLEncoder.encode(s, CharacterEnum.UTF8.getValue());
        System.out.println(_s);
        s = URLDecoder.decode(_s);
        System.out.println(s);
        s = URLDecoder.decode(_s, CharacterEnum.UTF8.getValue());
        System.out.println(s);
        s = URLDecoder.decode(_s, CharacterEnum.GBK.getValue());
        System.out.println(s);
    }
    
    /**
     * 多次转码的值是一样的
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void 两次转码() throws UnsupportedEncodingException {
        String encode = URLDecoder.decode(s, CharacterEnum.UTF8.getValue());
        System.out.println(encode);
        System.out.println(URLDecoder.decode(encode, CharacterEnum.UTF8.getValue()));
    }
}
