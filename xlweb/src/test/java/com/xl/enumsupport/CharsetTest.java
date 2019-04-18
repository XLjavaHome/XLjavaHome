package com.xl.enumsupport;

import java.nio.charset.Charset;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-07-06
 * @Time: 12:53
 * To change this template use File | Settings | File Templates.
 */
public class CharsetTest {
    @Test
    public void demoTest() {
        Charset charset = Charset.forName(CharsetEnum.UTF8.getValue());
        System.out.println(charset.name());
        System.out.println(CharsetEnum.UTF8.getValue());
        System.out.println(CharsetEnum.GBK.getValue());
    }
}