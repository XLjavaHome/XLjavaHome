package com.xl.base;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-04
 * @time 14:43
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class LogSlf4jTest {
    @Test
    public void name() {
        while (true) {
            log.info("这是当前时间{},{}", LocalDate.now(), LocalTime.now());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
