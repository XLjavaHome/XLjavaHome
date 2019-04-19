package com.xl.util;

import java.io.InputStream;
import org.apache.commons.discovery.tools.ResourceUtils;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-30
 * @Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class ResourceUtilTest {
    @Test
    public void getResourceInputStream() {
        InputStream resourceInputStream = ResourceUtil.getResourceInputStream("/test.txt");
        InputStream resourceInputStream2 = ResourceUtil.getResourceInputStream2("/src/src/main/resources/1.txt");
        InputStream resourceInputStream3 = ResourceUtil.getResourceInputStream3("src/src/main/resources/1.txt");
        System.out.println(resourceInputStream);
        System.out.println(resourceInputStream2);
        System.out.println(resourceInputStream3);
    }

    @Test
    public void apacheResourceTest() {
        //获取类所在包
        System.out.println(ResourceUtils.getPackageName(this.getClass()));
    }
    
    @Test
    public void write() {
    }
}