package com.xl.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * @Date: 2018-01-19
 * @Time: 13:51
 * To change this template use File | Settings | File Templates.
 */
public class JSONUtil {
    public static void main(String[] args) throws IOException {
        InputStream resourceInputStream = ResourceUtil.getResourceInputStream("src/src/main/resources/json/contact.json");
        System.out.println(IOUtil.getContent(resourceInputStream));
    }
}
