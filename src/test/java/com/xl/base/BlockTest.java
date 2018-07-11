package com.xl.base;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-07-02
 * @Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class BlockTest {
    static {
        log.info("这是静态代码块");
    }

    {
        log.info("这是代码块");
    }
    @Test
    public void demoTest() {
        //代码块每次生成对象的时候都执行
        new BlockTest();
        new BlockTest();
        new BlockTest();
        new BlockTest();
    }
}
