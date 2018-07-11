package com.xl.calendar;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * Date: 2017-11-20
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class DateTest {
    @Test
    public void test() {
        Date d = new Date();
        Date d2 = d;
        log.info(d.getMonth());
        log.info(d.getDay());
        log.info(d2.compareTo(d));//相等就输出
    }
}
