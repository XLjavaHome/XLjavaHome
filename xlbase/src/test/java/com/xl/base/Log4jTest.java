package com.xl.base;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-04
 * @time 14:51
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class Log4jTest {
    @Test
    public void name() {
        log.info(System.currentTimeMillis());
    }
}
