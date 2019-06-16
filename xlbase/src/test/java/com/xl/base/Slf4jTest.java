package com.xl.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-15
 * @time 23:21
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class Slf4jTest {
    @Test
    public void name() {
        log.info("这是{}测试！", 1);
    }
}
