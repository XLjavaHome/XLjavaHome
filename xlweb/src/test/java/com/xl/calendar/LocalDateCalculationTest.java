package com.xl.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-04-28
 * @time 14:51
 * To change this template use File | Settings | File Templates.
 */
public class LocalDateCalculationTest {
    /**
     * minus 减j
     * plus 加
     */
    @Test
    public void name() {
        //.简单的减（例如：增加天，月，年）
        int yearsToSubtract = 5;
        System.out.println(LocalDate.now().minusYears(yearsToSubtract));
        //LocalDate.now() 获取当前时间  // minusYears 减去一年
        //月，日，小时...
        System.out.println(LocalDate.now().minusMonths(yearsToSubtract));
        System.out.println(LocalDate.now().minusDays(yearsToSubtract));
        System.out.println(LocalDate.now().plusDays(yearsToSubtract));
        //2.简单的增加
        System.out.println(LocalDate.now().plusYears(yearsToSubtract));//增加一年
        //月，日，小时...
        System.out.println(LocalDate.now().plusDays(yearsToSubtract));
        //3.获取当前日期，获取所在周星期一， 二， 三， 四..（周四为例）
        LocalDate planDate = LocalDate.now();//可以自定义时间，这里取当前时间
        LocalDate reviewTime = planDate.with(DayOfWeek.THURSDAY);//DayOfWeek 枚举 可以随意获取星期几
        System.out.println(reviewTime.toString());
    }
}
