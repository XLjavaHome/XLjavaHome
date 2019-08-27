package com.xl.thread.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created with 徐立.在并发编程场景下如何保证车票或者商品出售业务是线程安全的,用ConcurrentLinkedQueue
 *
 * @author 徐立
 * @date 2019-08-27
 * @time 9:05
 * To change this template use File | Settings | File Templates.
 */
public class ConcurrentLinkedQueueTest {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();
    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("车票/商品编号：" + i);
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }// 取出元素
                    String ticket = tickets.poll();
                    if (ticket == null) {
                        break;
                    } else {
                        System.out.println(
                                String.format("线程[%s][%s]卖出%s", Thread.currentThread().getId(), Thread.currentThread().getName(),
                                              ticket));
                    }
                }
            }).start();
        }
    }
}
