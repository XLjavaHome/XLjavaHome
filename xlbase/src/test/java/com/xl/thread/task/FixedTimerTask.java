package com.xl.thread.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TimerTask;

/**
 * Created with 徐立.不可动态修改的任务
 *
 * @author 徐立
 * @date 2019-07-27
 * @time 6:46
 * To change this template use File | Settings | File Templates.
 */
public class FixedTimerTask extends TimerTask {
    @Override
    public void run() {
        String d = getNow();
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("已执行【" + (i + 1) + "】秒钟，at: " + d);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("本次任务调度结束，at: " + getNow());
        System.out.println("---------------------------");
    }
    
    private String getNow() {
        return LocalDate.now() + ":" + LocalTime.now();
    }
}
