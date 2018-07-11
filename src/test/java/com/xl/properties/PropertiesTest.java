package com.xl.properties;

import com.xl.util.PropertiesUtil;
import com.xl.util.ResourceUtil;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-02-08
 * @Time: 16:55
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class PropertiesTest {
    @Test
    public void readDemo() throws IOException {
        Map<String, String> map = PropertiesUtil.readProperties(ResourceUtil.getResourceFile("properties/obj.properties"));
        System.out.println(map);
    }
}
