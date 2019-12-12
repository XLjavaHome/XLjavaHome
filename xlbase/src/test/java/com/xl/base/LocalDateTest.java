package com.xl.base;

import com.xl.util.DateUtil;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.经过计算会产生新的对象
 *
 * @author 徐立
 * @date 2019-07-17
 * @time 8:51
 * To change this template use File | Settings | File Templates.
 */
public class LocalDateTest {
    @Test
    void name() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);
        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        independenceDay = LocalDate.of(2014, 1, 4);
        System.out.println(independenceDay);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        LocalDate parse = LocalDate.parse("2018-07-04");
        System.out.println(parse);
        System.out.println(today.isBefore(parse));
    }
    
    @Test
    void 初始化() {
        LocalDate now = LocalDate.now();
        LocalDate localDate1 = now.minusMonths(1);
        System.out.println(localDate1);
        //替换后的对象和原对象不一致
        LocalDate localDate = now.withDayOfMonth(1);
        System.out.println(localDate);
        System.out.println(localDate.minusDays(1));
        System.out.println(localDate);
    }
    
    @Test
    void get() {
        // 年份
        LocalDate.now().getYear();
        // 月份（数值表示， 从1开始）
        LocalDate.now().getMonthValue();
        // 月份（英文[enum]表示）
        LocalDate.now().getMonth();
        // 日期（从1开始）
        LocalDate.now().getDayOfMonth();
        // 当天所在这一年的第几天（从1开始）
        LocalDate.now().getDayOfYear();
        // 星期几
        LocalDate.now().getDayOfWeek();
        // 当年的天数
        LocalDate.now().lengthOfYear();
        //当月的天数
        LocalDate.now().lengthOfMonth();
        // 与时间纪元（1970年1月1日）相差的天数，负数表示在时间纪元之前多少天
        LocalDate.now().toEpochDay();
    }
    
    @Test
    void toDate() {
        LocalDate now = LocalDate.now();
        Date date = Date.from(now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(DateUtil.formatDate(date));
    }
    
    @Test
    void dateToLocalDate() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("LocalDate = " + localDate);
    }
    
    @Test
    void dateTest() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.toLocalDateTime().toLocalDate());
    }
    
    @Test
    void dateTest2() {
        //防止为空
        Timestamp timestamp = null;
        LocalDate localDate = Optional.ofNullable(timestamp).map(Timestamp::toLocalDateTime).map(LocalDateTime::toLocalDate)
                                      .orElse(null);
        System.out.println(localDate);
    }
    
    @Test
    void streamToMap() {
        //包含头，不包含尾
        List<Integer> ints = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        Map<Integer, String> collect = IntStream.range(1, 10).boxed().collect(Collectors.toMap(k -> k, v -> v + "", (o, n) -> n));
        System.out.println(collect);
        System.out.println(ints);
    }
    
    @Test
    void nowTest() {
        System.out.println(LocalDate.now() + " " + LocalTime.now());
        //当前时间用LocalDateTime
        System.out.println(LocalDateTime.now());
    }
}


