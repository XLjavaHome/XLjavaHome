package com.xl.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2019-01-05
 * @Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ExecutorService poolExecutor = new ThreadPoolExecutor(5, 10, 300, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
        Runnable run = () -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "正在执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(run);
        }
        //这里一定要做关闭
        poolExecutor.shutdown();
    }
}
