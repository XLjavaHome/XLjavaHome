package com.xl.thread;

import lombok.extern.log4j.Log4j;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-12-22
 * @Time: 19:47
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class ThreadLocalTest {
    public static class MyRunnable implements Runnable {
        private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
        
        @Override
        public void run() {
            int value = (int) (Math.random() * 100D);
            log.info(Thread.currentThread().getId() + ":" + value);
            threadLocal.set(value);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            log.info(Thread.currentThread().getId() + ":" + threadLocal.get());
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        MyRunnable sharedRunnableInstance = new MyRunnable();
        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
