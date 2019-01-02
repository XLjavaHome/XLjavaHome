package com.xl.util;

import java.io.IOException;
import java.util.List;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-12-28
 * @Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class CMDUtilTest {
    /**
     * 测试
     */
    @Test
    public void demoTest() throws IOException, InterruptedException {
        List<String> result = CMDUtil.getCMDINfo("pwd");
        System.out.println(StringUtil.join(result, "\n"));
    }
}