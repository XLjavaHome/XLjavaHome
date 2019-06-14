package com.xl.base;

import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-11
 * @time 23:58
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionTest {
    @Test
    public void name() {
        for (int i = 0; i < 1000; i++) {
            for (int j = 1000; j >= 0; j--) {
                String s = null;
                s.split("11");
                System.out.println(i / j);
            }
        }
    }
}
