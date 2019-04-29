package com.xl.calendar;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-04-28
 * @time 14:50
 * To change this template use File | Settings | File Templates.
 */
public class LocalDateTest {
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
        LocalDate date1 = LocalDate.of(2019, 4, 1);
        System.out.println(date1);
        if (date1.equals(today)) {
            System.out.println(String.format("Today %s and date1 %s are same date %n", today, date1));
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
    
    @Test
    public void date和localDate转换() {
        //1）将java.util.Date转换为ZonedDateTime。
        //2）使用它的toLocalDate（）方法从ZonedDateTime获取LocalDate。
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        System.out.println("Date = " + date);
        System.out.println("LocalDate = " + localDate);
    }
    
    @Test
    public void LocalDate转Date() {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());
        System.out.println("LocalDate = " + localDate);
        System.out.println("Date = " + date);
    }
    
    @Test
    public void name() {
        System.out.println(Clock.systemDefaultZone());
    }
}
