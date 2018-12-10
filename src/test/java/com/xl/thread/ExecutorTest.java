package com.xl.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with 徐立.线程池,会比普通的线程快
 *
 * @author: 徐立
 * @Date: 2018-11-24
 * @Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
public class ExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        newSingleThreadExecutor();
       
        
        /*
        //Executors 线程的工具类
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
            }
        });
        */
    }
    
    private static List<Integer> list = new ArrayList<>(10000);
    
    private static void newSingleThreadExecutor() throws InterruptedException {
        long start = System.currentTimeMillis();
        Random rm = new Random();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    list.add(rm.nextInt());
                }
            });
        }
        //线程不会执行完
        System.out.println(list.size());
        System.out.println(list.size());
        //启动有序关闭，其中先前提交的任务将被执行，但不会接受任何新任务。
        executorService.shutdown();
        //阻止所有任务在关闭请求完成后执行，或发生超时，或当前线程中断，以先到者为准。 调用该方法线程才能执行结束
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(list.size());
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
