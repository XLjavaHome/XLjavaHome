package com.xl.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with 徐立.锁测试
 *
 * @author 徐立
 * @date 2019-08-28
 * @time 17:31
 * To change this template use File | Settings | File Templates.
 */
public class LockTest implements Runnable {
    // 定义火车票
    private int tickets = 200;
    // 定义锁对象
    private Lock lock = new ReentrantLock();
    
    public static void main(String[] args) {
        Runnable s = new LockTest();
        Thread t1 = new Thread(s, "窗口1");
        Thread t2 = new Thread(s, "窗口2");
        Thread t3 = new Thread(s, "窗口3");
        Thread t4 = new Thread(s, "窗口4");
        Thread t5 = new Thread(s, "窗口5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                // 加锁
                //lock.lock();
                //***************业务处理******************
                synchronized (this) {
                    if (tickets < 0) {
                        break;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "正在出售第" + (tickets--) + "张票");
                }
                //*****************************************
            } finally {
                // 释放锁
                //lock.unlock();
            }
        }
    }
}
