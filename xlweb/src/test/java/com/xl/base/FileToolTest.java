package com.xl.base;

import com.xl.util.ResourceUtil;
import java.io.UnsupportedEncodingException;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017/10/16
 * Time: 17:14
 * To change this template use File | Settings | File Templates.
 */
public class FileToolTest {
    @Test
    public void pathTest() throws UnsupportedEncodingException {
        System.out.println(ResourceUtil.getResourceInputStream("src/src/main/resources/1.txt"));
    }
}
