package com.xl.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with 徐立.线程池,会比普通的线程快,线程运行完毕后会运行之后的线程
 *
 * @author: 徐立
 * @Date: 2018-11-24
 * @Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
public class ExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Random rm = new Random();
        ExecutorService executorService = null;
        //Executors.newSingleThreadExecutor();
        executorService = Executors.newFixedThreadPool(5);
        demo(executorService);
        //线程不会执行完
        //启动有序关闭，其中先前提交的任务将被执行，但不会接受任何新任务。
        executorService.shutdown();
        demo(executorService);
        System.out.println("完毕");
        //阻止所有任务在关闭请求完成后执行，或发生超时，或当前线程中断，以先到者为准。 调用该方法线程才能执行结束
        //executorService.awaitTermination(1, TimeUnit.DAYS);
       
        
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
    
    public static void demo(ExecutorService executorService) {
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    System.out.println(finalI);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
