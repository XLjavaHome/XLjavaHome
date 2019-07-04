package com.xl;

import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-30
 * @time 14:04
 * To change this template use File | Settings | File Templates.
 */
public class SystemTest {
    @Test
    void name() {
        //系统计时器的当前值，以毫微秒为单位。
        System.out.println(System.nanoTime());
        //返回以毫秒为单位的当前时间。
        System.out.println(System.currentTimeMillis());
    }
}
