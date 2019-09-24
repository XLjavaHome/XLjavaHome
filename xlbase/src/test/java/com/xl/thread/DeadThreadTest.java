package com.xl.thread;

import java.text.MessageFormat;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-24
 * @time 23:35
 * To change this template use File | Settings | File Templates.
 */
public class DeadThreadTest implements Runnable {
    static int flag = 1;
    static Object o1 = new Object();
    static Object o2 = new Object();
    
    public static void main(String[] args) throws InterruptedException {
        DeadThreadTest t1 = new DeadThreadTest();
        DeadThreadTest t2 = new DeadThreadTest();
        t1.flag = 1;
        new Thread(t1).start();
        Thread.sleep(1000);
        t2.flag = 0;
        new Thread(t2).start();
    }
    
    @Override
    public void run() {
        if (flag == 1) {
            synchronized (o1) {
                try {
                    System.out.println(MessageFormat.format("我是{0}锁住o1", Thread.currentThread().getName()));
                    Thread.sleep(3000);
                    System.out.println(MessageFormat.format("{0},醒来准备获取o2", Thread.currentThread().getName()));
                    synchronized (o2) {
                        System.out.println(MessageFormat.format("{0}拿到o2的锁", Thread.currentThread().getName()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (flag == 0) {
            synchronized (o2) {
                try {
                    System.out.println(MessageFormat.format("我是{0}锁住o2", Thread.currentThread().getName()));
                    Thread.sleep(3000);
                    System.out.println(MessageFormat.format("{0},醒来准备获取o2", Thread.currentThread().getName()));
                    synchronized (o1) {
                        System.out.println(MessageFormat.format("{0}拿到o1的锁", Thread.currentThread().getName()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
