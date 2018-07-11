package com.xl.base;

import com.xl.XLTest;
import lombok.extern.log4j.Log4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017/10/9
 * Time: 13:57
 * To change this template use File | Settings | File Templates.
 */
@Log4j
//@CommonsLog
//@lombok.extern.java.Log
public class LogTest {
    private static final Log log2 = LogFactory.getLog(XLTest.class);

    @Test
    public void logTest() {
        log2.info("测试");
    }

    @Test
    public void demoTest() {
        log.info("测试");
    }
}
