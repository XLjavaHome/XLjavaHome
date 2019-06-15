package com.xl.util;

import org.testng.annotations.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-30
 * @time 9:12
 * To change this template use File | Settings | File Templates.
 */
public class RegexTest {
    @Test
    public void testRegularReplacement() {
        StringBuffer result = new StringBuffer();
        RegexUtil.regularReplacement("11111111111111111aaaaaaaaa43434\n231", "\\d+", "", result);
        ClassLoaderTest.log.info(result);
    }
}
