package com.xl.util;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-01-19
 * @Time: 13:51
 * To change this template use File | Settings | File Templates.
 */
public class JSONUtil {
    public static String getJSONStr() throws IOException {
        File file = ResourceUtil.getResourceFile("json/contact.json");
        return FileUtil.getContent(file);
    }
}
