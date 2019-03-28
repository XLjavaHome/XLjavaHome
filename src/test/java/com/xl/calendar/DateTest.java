package com.xl.calendar;

import com.xl.util.DateUtil;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
    
    @Test
    public void 获取当天的日期() {
        LocalDate today = LocalDate.now();
        //当天日期不包含时间
        System.out.println(today);
        System.out.println(today.toString());
    }
    
    @Test
    public void 获取当前的年月日() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.printf("Year : %d Month : %d day : %d \t %n", year, month, day);
    }
    
    @Test
    public void 检查两个日期是否相等() {
        LocalDate today = LocalDate.now();
        LocalDate date1 = LocalDate.of(2019, 03, 28);
        if (date1.equals(today)) {
            System.out.printf("Today %s and date1 %s are same date %n", today, date1);
        }
    }
    
    @Test
    public void 增加时间里面的小时数() {
        LocalTime time = LocalTime.now();
        LocalTime newTime = time.plusHours(2); // adding two hours
        System.out.println("现在的时间 " + time);
        System.out.println("2个小时之后的时间 " + newTime);
    }
    
    @Test
    public void 获取1周后的日期() {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Today is : " + today);
        System.out.println("Date after 1 week : " + nextWeek);
    }
    
    @Test
    public void 一年前后的日期() {
        LocalDate today = LocalDate.now();
        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("Date before 1 year : " + previousYear);
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("Date after 1 year : " + nextYear);
    }
    
    @Test
    public void 检查闰年() {
        LocalDate today = LocalDate.now();
        if (today.isLeapYear()) {
            System.out.println("This year is Leap year");
        } else {
            System.out.println("2018 is not a Leap year");
        }
    }
    
    @Test
    public void 获取当前时间戳() {
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp);
    }
    
    @Test
    public void 日期进行格式化转换成字符串() {
        LocalDateTime arrivalDate = LocalDateTime.now();
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            String landing = arrivalDate.format(format);
            System.out.printf("Arriving at : %s %n", landing);
        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", arrivalDate);
            ex.printStackTrace();
        }
    }
}
