package com.xl.thread;

import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA. Semaphore(信号量)实际上就是可以控制同时访问的线程个数，它维护了一组**"许可证"**。
 * <p>
 * 当调用acquire()方法时，会消费一个许可证。如果没有许可证了，会阻塞起来
 * 当调用release()方法时，会添加一个许可证。
 * 这些"许可证"的个数其实就是一个count变量罢了~
 *
 * @author: 徐立
 * @Date: 2018-07-27
 * @Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
public class SemaphoreTest {
    /**
     * 每次只能5个客户同时进酸奶小店购买挑选
     *
     * @param args
     */
    public static void main(String[] args) {
        // 假设有50个同时来到酸奶店门口
        int nums = 50;
        // 酸奶店只能容纳10个人同时挑选酸奶
        Semaphore semaphore = new Semaphore(10);
        for (int i = 0; i < nums; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    // 有"号"的才能进酸奶店挑选购买
                    semaphore.acquire();
                    System.out.println("顾客" + finalI + "在挑选商品，购买...");
                    // 假设挑选了xx长时间，购买了
                    Thread.sleep(1000);
                    // 归还一个许可，后边的就可以进来购买了
                    System.out.println("顾客" + finalI + "购买完毕了...");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
