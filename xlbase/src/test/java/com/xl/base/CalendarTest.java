package com.xl.base;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-23
 * @time 11:34
 * To change this template use File | Settings | File Templates.
 */
public class CalendarTest {
    @Test
    public void name() {
        Calendar instance = Calendar.getInstance();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        System.out.println(gregorianCalendar);
    }
}
