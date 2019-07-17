package com.xl.base;

import com.xl.util.DateUtil;
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
    public void 获取每个月5号() {
        Calendar fiveCalendar = Calendar.getInstance();
        fiveCalendar.set(Calendar.DAY_OF_MONTH, 5);
        printCalender(fiveCalendar);
        Calendar sixCalendar = Calendar.getInstance();
        //从1开始
        sixCalendar.set(Calendar.DAY_OF_MONTH, 1);
        printCalender(sixCalendar);
    }
    
    private void printCalender(Calendar fiveCalendar) {
        System.out.println(DateUtil.formatDate(fiveCalendar.getTime()));
    }
    
    @Test
    public void 测试() {
        Calendar lastBusinessDayOfPreviousMonth = Calendar.getInstance();
        lastBusinessDayOfPreviousMonth.set(Calendar.DAY_OF_MONTH, 1);
        printCalender(lastBusinessDayOfPreviousMonth);
    }
    
    @Test
    public void name() {
        Calendar instance = Calendar.getInstance();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        System.out.println(gregorianCalendar);
    }
}
