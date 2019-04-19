package com.xl.util;

import java.io.InputStream;
import java.util.Properties;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-04-19
 * @time 17:21
 * To change this template use File | Settings | File Templates.
 */
public class PropertiesUtilTest {
    @Test
    public void loadProperties() {
        InputStream resourceInputStream = ResourceUtil.getResourceInputStream("log4j.properties");
        Properties properties = PropertiesUtil.loadProperties(resourceInputStream);
        System.out.println(properties);
    }
}