package com.xl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取时间方法
 *
 * @author wzj
 * @date 2014-2-25
 */
public class DatePick {
    private final static String WEEK_START = "wkstart";
    private final static String WEEK_END = "wkend";
    private final static String MONTH_START = "mostart";
    private final static String MONTH_END = "moend";

    public static String getWeekStart() {
        return WEEK_START;
    }

    public static String getWeekEnd() {
        return WEEK_END;
    }

    public static String getMonthStart() {
        return MONTH_START;
    }

    public static String getMonthEnd() {
        return MONTH_END;
    }

    /**
     * 根据当前时间获取本月的时间区间，以每月的第一个周一开始，月最后一天所在周的周日结束
     *
     * @param date
     * @param meth
     * @return
     */
    public String monthInterval(Date date, String meth) {
        return this.monthInterval(this.convertDateToString(date), meth);
    }

    /**
     * 根据当前时间获取本月的时间区间，以每月的第一个周一开始，月最后一天所在周的周日结束
     *
     * @param date
     * @param meth
     * @return
     */
    public String monthInterval(String date, String meth) {
        String res = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd");
        if (null != date && !"".equals(date)) {
            try {
                Date d = sdf.parse(date);
                cal.setTime(d);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                int i = 1;
                while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                    cal.set(Calendar.DAY_OF_MONTH, i++);
                }
                if (MONTH_START.equals(meth)) {
                    res = sdf_date.format(cal.getTime());
                } else if (MONTH_END.equals(meth)) {
                    /* set 本月最后一天 */
                    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                    /* 如果本月最后一天是星期天表示本月结束，否则就在本月的最后一个星期天的基础上加一周 */
                    if (cal.get(Calendar.WEEK_OF_MONTH) != 6) {
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                        /* 增加一个星期，才是我们中国人理解的本周日的日期 */
                        cal.add(Calendar.WEEK_OF_YEAR, 1);
                    }
                    res = sdf_date.format(cal.getTime());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    /**
     * 日期由date类型转为string类型
     *
     * @param date
     * @return
     */
    private String convertDateToString(Date date) {
        String res = "";
        if (date != null) {
            Date currentTime = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            res = sdf.format(currentTime);
        }
        return res;
    }

    /**
     * 根据年月计算当月有几周，以每月的第一个周一开始，月最后一天所在周的周日结束
     *
     * @param date
     * @return
     */
    public long countWeeksInMonth(Date date) {
        return countWeeksInMonth(this.convertDateToString(date));
    }

    /**
     * 根据年月计算当月有几周，以每月的第一个周一开始，月最后一天所在周的周日结束
     *
     * @param date
     * @return
     */
    public long countWeeksInMonth(String date) {
        long res = 0;
        String sbegin = this.monthInterval(date, MONTH_START);
        String send = this.monthInterval(date, MONTH_END);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (sbegin != null && !"".equals(sbegin) && send != null && !"".equals(send)) {
            try {
                Date dbegin = sdf.parse(sbegin);
                Date dend = sdf.parse(send);
                long cdays = dend.getTime() - dbegin.getTime();
                res = cdays / 1000 / 60 / 60 / 24;
                res = (res + 1) / 7;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    /**
     * 根据年月和周次查询当周的起日或止日日期,以每月的第一个周一开始，月最后一天所在周的周日结束
     *
     * @param date
     * @param week
     * @param meth
     * @return
     */
    public String getWeekDate(Date date, int weekNo, String meth) {
        return getWeekDate(this.convertDateToString(date), weekNo, meth);
    }

    /**
     * 根据年月和周次查询当周的起日或止日日期,以每月的第一个周一开始，月最后一天所在周的周日结束
     *
     * @param date
     * @param week
     * @param meth
     * @return
     */
    public String getWeekDate(String date, int weekNo, String meth) {
        String res = "";
        String sbegin = this.monthInterval(date, MONTH_START);
        long weeks = this.countWeeksInMonth(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        if (weekNo > 0 && weekNo <= weeks && sbegin != null && !"".equals(sbegin)) {
            try {
                Date d = sdf.parse(sbegin);
                cal.setTime(d);
                cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 7 * (weekNo - 1));
                if (WEEK_START.equals(meth)) {
                    res = sdf.format(cal.getTime());
                } else if (WEEK_END.equals(meth)) {
                    cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 6);
                    res = sdf.format(cal.getTime());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    /**
     * 判断给定年月周次和当前所处时间的周次差值是否在允许范围内
     *
     * @param date
     * @param week
     * @param scope
     */
    public boolean weekInScopeFromNow(Date date, int week, int scope) {
        return this.weekInScopeFromNow(this.convertDateToString(date), week, scope);
    }

    /**
     * 判断给定年月周次和当前所处时间的周次差值是否在允许范围内
     *
     * @param date
     * @param week
     * @param scope
     */
    public boolean weekInScopeFromNow(String date, int weekNo, int scope) {
        boolean res = false;
        String sta = weekInterval(new Date(), WEEK_START);
        String end = getWeekDate(date, weekNo, WEEK_START);
        if (sta != null && !"".equals(sta) && end != null && !"".equals(end)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date staD = sdf.parse(sta);
                Date endD = sdf.parse(end);
                long jump = scope * (-7);
                long cdays = staD.getTime() - endD.getTime();
                cdays = cdays / 1000 / 60 / 60 / 24;
                if ((0 <= cdays && cdays <= jump) || (jump <= cdays && cdays <= 0)) {
                    res = true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    /**
     * 根据当前时间获取本周的时间区间，以周一开始周日结束
     *
     * @param date
     * @return
     * @throws Exception
     */
    public String weekInterval(Date date, String meth) {
        return this.weekInterval(this.convertDateToString(date), meth);
    }

    /**
     * 根据当前时间获取本周的时间区间，以周一开始周日结束
     *
     * @param date
     * @param meth
     * @return
     * @throws ParseException
     */
    public String weekInterval(String date, String meth) {
        String res = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (null != date && !"".equals(date)) {
            try {
                Date d = sdf.parse(date);
                cal.setTime(d);
                /* 如果当天是周日则减一周，因为国外吧周日作为每周第一天 */
                if (Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK)) {
                    cal.add(Calendar.WEEK_OF_YEAR, -1);
                }
                if (WEEK_START.equals(meth)) {
                    /* 获取本周一的日期 */
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                    res = sdf.format(cal.getTime());
                } else if (WEEK_END.equals(meth)) {
                    /* 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天 */
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                    /* 增加一个星期，才是我们中国人理解的本周日的日期 */
                    cal.add(Calendar.WEEK_OF_YEAR, 1);
                    res = sdf.format(cal.getTime());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    /**
     * 判断给定年月和当前所处年月的月份差值是否在允许范围内
     *
     * @param date
     * @param scope 正数表示当前时间之后，负数表示当前时间之前
     * @return
     */
    public boolean monthInScopeFromNow(Date date, int scope) {
        return this.monthInScopeFromNow(this.convertDateToString(date), scope);
    }

    /**
     * 判断给定年月和当前所处年月的月份差值是否在允许范围内
     *
     * @param date
     * @param scope 正数表示当前时间之后，负数表示当前时间之前
     * @return
     */
    public boolean monthInScopeFromNow(String date, int scope) {
        boolean res = false;
        long scmonth = this.countMonthesToNow(date, "month");
        if (scmonth > Long.MIN_VALUE) {
            if ((scope <= scmonth && scmonth <= 0) || (0 <= scmonth && scmonth <= scope)) {
                res = true;
            }
        }
        return res;
    }

    /**
     * 计算给定日期和当前日期相隔月数或者季度，如果返回值为Long.MIN_VALUE则表示计算出错
     *
     * @param date
     * @param type 其值为month表示求月份差值，为quarter表示求季度差值
     * @return
     */
    public long countMonthesToNow(String date, String type) {
        long res = Long.MIN_VALUE;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        if (date != null && !"".equals(date)) {
            try {
                Date dorg = sdf.parse(date);
                int orgY = 0, nowY = 0;
                int orgM = 0, nowM = 0;
                cal.setTime(dorg);
                orgY = cal.get(Calendar.YEAR);
                orgM = cal.get(Calendar.MONTH);
                cal.setTime(new Date());
                nowY = cal.get(Calendar.YEAR);
                nowM = cal.get(Calendar.MONTH);
                if ("month".equals(type)) {
                    res = (orgY - nowY) * 12 + orgM - nowM;
                } else if ("quarter".equals(type)) {
                    /* 由于Calendar.MONTH中将一月的返回值定为0，所以这里把月份值都加上1再处理 */
                    res = (orgY - nowY) * 4 + (orgM + 1) / 4 - (nowM + 1) / 4;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    /**
     * 判断给定年月所在季度和当前所处季度的季度差值是否在允许范围内
     *
     * @param date
     * @param scope 正数表示当前时间之后，负数表示当前时间之前
     * @return
     */
    public boolean quarterInScopeFromNow(Date date, int scope) {
        return this.monthInScopeFromNow(this.convertDateToString(date), scope);
    }

    /**
     * 判断给定年月所在季度和当前所处季度的季度差值是否在允许范围内
     *
     * @param date
     * @param scope 正数表示当前季度之后，负数表示当前季度之前
     * @return
     */
    public boolean quarterInScopeFromNow(String date, int scope) {
        boolean res = false;
        long scmonth = this.countMonthesToNow(date, "quarter");
        if (scmonth > Long.MIN_VALUE) {
            if ((scope <= scmonth && scmonth <= 0) || (0 <= scmonth && scmonth <= scope)) {
                res = true;
            }
        }
        return res;
    }
}
