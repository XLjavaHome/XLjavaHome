package com.xl.base;

import java.math.BigDecimal;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-03
 * @time 9:11
 * To change this template use File | Settings | File Templates.
 */
public class BigDecimalTest {
    @Test
    public void name() {
        BigDecimal a = new BigDecimal("2");
        BigDecimal b = new BigDecimal("3");
        ClassLoaderTest.log.info(a.add(b));
    }
}
