package com.xl.base;

import com.xl.util.CMDUtil;
import java.io.IOException;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017/10/11
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
public class JavaTest {
    /**
     *
     */
    @Test
    public void demoTest() {
        System.out.println(1 << 4);
    }
    
    @Test
    public void CloseJavaTest() throws IOException {
        CMDUtil.closeJava();
        System.out.println("关闭成功");
    }
}

