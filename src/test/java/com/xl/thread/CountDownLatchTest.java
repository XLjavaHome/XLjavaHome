package com.xl.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.CountDownLatch是一个同步的辅助类，允许一个或多个线程一直等待，直到其它线程完成它们的操作。
 * count初始化CountDownLatch，然后需要等待的线程调用await方法。await方法会一直受阻塞直到count=0。而其它线程完成自己的操作后，调用countDown()使计数器count减1。当count减到0时，所有在等待的线程均会被释放
 * 说白了就是通过count变量来控制等待，如果count值为0了(其他线程的任务都完成了)，那就可以继续执行。
 * <p>
 *
 * @author: 徐立
 * @Date: 2018-07-27
 * @Time: 12:38
 * To change this template use File | Settings | File Templates.
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        /**
         * 下面的for循环要保持一致
         */
        int count = 5;
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        System.out.println("现在6点下班了.....");
        // 3y线程启动
        new Thread(() -> {
            try {
                // 这里调用的是await()不是wait()
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("...其他的5个员工走光了，3y终于可以走了");
        }).start();
        // 其他员工线程启动
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                System.out.println("员工xxxx下班了");
                //
                countDownLatch.countDown();
            }).start();
        }
    }
}
