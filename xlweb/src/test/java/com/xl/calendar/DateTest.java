package com.xl.calendar;

import com.xl.util.DateUtil;
import java.util.Calendar;
import java.util.Date;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

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
    public void addDayTest() {
        Date d = new Date();
        Date d2 = d;
        //当前日期加一天
        Date date = DateUtils.addDays(d, 1);
        System.out.println(DateUtil.formatDate(date));
    }
    
    /**
     * 新日期=原日期，改变新日期后原日期也发送改变
     */
    @Test
    public void data1Test() {
        Calendar now = Calendar.getInstance();
        Calendar newCal = now;
        newCal.set(Calendar.DATE, 0);
        System.out.println(DateUtil.formatDate(now.getTime(), "yyyy-MM-dd"));
    }
    
    /**
     * 相等的日期就返回true
     */
    @Test
    public void equalTest() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR, 2);
        System.out.println(now.get(Calendar.ERA));
        System.out.println(now.get(0));
        System.out.println(now.get(1));
        System.out.println(now.get(2));
        System.out.println(DateUtils.isSameDay(now, Calendar.getInstance()));
        System.out.println(org.apache.commons.lang3.time.DateUtils.isSameDay(now, Calendar.getInstance()));
    }
    
   
}
