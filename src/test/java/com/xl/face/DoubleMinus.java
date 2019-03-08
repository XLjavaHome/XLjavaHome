package com.xl.face;

import java.math.BigDecimal;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

@Log4j
public class DoubleMinus {
    /**
     * 测试
     */
    @Test
    public void demoTest() {
        log.info(new BigDecimal("2.00").subtract(new BigDecimal("1.1")));
        log.info(String.format("%.1f", 2.0 - 1.1));
        //不是精确计算
        log.info(2.0 - 1.1);
    }
}
