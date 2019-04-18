package com.xl;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-03-10
 * @time 17:51
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class ExceptionTest {
    @Test
    public void exceptionTest() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            //中括号无法将异常除外
            log.warn("测试中括号:{}", e);
        }
    }
}
