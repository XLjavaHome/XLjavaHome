package com.xl.util;

import com.xl.service.ISchedule;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-06-01
 * @Time: 9:24
 * To change this template use File | Settings | File Templates.
 */
public class TimerUtil {
    /**
     * 在几耗秒后执行一次定时任务
     *
     * @param schedule
     * @param time     毫秒
     */
    public static void schedule(ISchedule schedule, long time) {
        TimerTask timeTask = new TimerTask() {
            @Override
            public void run() {
                schedule.schedule();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timeTask, time);
    }
}
