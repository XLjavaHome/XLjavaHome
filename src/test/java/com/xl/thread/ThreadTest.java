package com.xl.thread;

import lombok.extern.log4j.Log4j;

/**
 * 4.【强制】线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
 * 说明：Executors返回的线程池对象的弊端如下：
 * 1)FixedThreadPool和SingleThreadPool：
 * 允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
 * 2)CacheThreadPooL和ScheduledThreadPool：
 * 允许的创建线程数量为Integer.MAX_VALUE，可能会去创建大量的线程，从而导致OOM。
 * 线程一旦终止不能start    <p/>
 * 类锁获取后可以获取到对象锁，没有任何互斥
 *
 * @see java.lang.Thread.State   线程有7中状态 ，该枚举有6种
 * 先wait再notify
 */
@Log4j
public class ThreadTest {
    /**
     * volatile 让主线程感知
     */
    private int num = 0;
    
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            //isAlive是否在运行
            int finalI = i;
            new Thread(() -> {
                log.info(finalI);
                //Thread.sleep(2000);
                Thread.yield();
                log.info(finalI);
                System.out.println(finalI);
                //System.out.println(thread.isAlive());
            }).start();
        }
    }
}
