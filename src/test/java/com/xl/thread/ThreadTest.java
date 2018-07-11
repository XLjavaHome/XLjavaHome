package com.xl.thread;

import org.junit.Test;

/**
 * 4.【强制】线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
 * 说明：Executors返回的线程池对象的弊端如下：
 * 1)FixedThreadPool和SingleThreadPool：
 * 允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
 * 2)CacheThreadPooL和ScheduledThreadPool：
 * 允许的创建线程数量为Integer.MAX_VALUE，可能会去创建大量的线程，从而导致OOM。
 */
public class ThreadTest implements Runnable {
    /**
     * volatile 让主线程感知
     */
    private  int num = 0;

    @Override
    public void run() {
        for (; num < 5; num++) {
            try {
                Thread.sleep(1000);
                System.out.println(num);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test1() throws InterruptedException {
        System.out.println("这是主线程：" + num);
        new Thread(this).start();
        new Thread(() -> System.out.println("num的值"+num)).start();
        Thread.sleep(1);
        System.out.println("这是主线程2：" + num);
    }
}
