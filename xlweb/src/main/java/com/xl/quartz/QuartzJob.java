package com.xl.quartz;
/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 10/21 0021
 * Time: 9:58
 * To change this template use File | Settings | File Templates.
 */

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 任务执行类
 */
public class QuartzJob implements Job {
    @Override
    public void execute(JobExecutionContext content) throws JobExecutionException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "★★★★★★★★★★★");
        String jobName = content.getJobDetail().getName();
        JobDataMap dataMap = content.getJobDetail().getJobDataMap();
        String param = dataMap.getString("param");
        System.out.println("传递的参数是=" + param + "任务名字是=" + jobName);
    }
}