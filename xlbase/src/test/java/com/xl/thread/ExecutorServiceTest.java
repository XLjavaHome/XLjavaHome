package com.xl.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with 徐立. 线程池测试
 *
 * @author 徐立
 * @date 2019-09-28
 * @time 15:36
 * To change this template use File | Settings | File Templates.
 */
public class ExecutorServiceTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(() -> System.out.println("hello"));
        executorService.submit(() -> {
            try {
                //出错了不会报错
                int i = 1 / 0;
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //没有shutdown jvm不会死 ,shutdown后再接入线程会报错，用execute报错
        executorService.execute(() -> {
            int i = 1 / 0;
            print();
        });
        executorService.execute(() -> {
            print();
        });
        executorService.shutdown();
        System.out.println("能否执行,可以执行!");
    }
    
    private static void print() {
        System.out.println(" world");
    }
}
