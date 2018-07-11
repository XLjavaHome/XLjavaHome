package com.xl.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorDemo {
    private static Integer pages = 1; // 网页数
    private static boolean exeFlag = true; // 执行标识

    public static void main(String[] args) {
        //线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
        ExecutorService executorService = Executors.newFixedThreadPool(10); // 创建ExecutorService 连接池默认连接10个
        while (exeFlag) {
            if (pages <= 100) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("爬取了第" + pages + "网页...");
                        pages++;
                    }
                });
            } else {
                if (((ThreadPoolExecutor) executorService).getActiveCount() == 0) { // 活动线程个数是0
                    executorService.shutdown(); // 结束所有线程
                    exeFlag = false;
                    System.out.println("爬虫任务已经完成");
                }
            }
            try {
                Thread.sleep(100); // 线程休息0.1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
