package com.xl.base;

import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-09
 * @Time: 13:06
 * To change this template use File | Settings | File Templates.
 */
public class BrowserTest {
    @Test
    public void openTest() throws IOException {
        Desktop.getDesktop().browse(URI.create("http://127.0.0.1:8080/bbs/bbs/index"));
        Desktop.getDesktop().browse(URI.create("http://www.hao123.com"));
    }
}
