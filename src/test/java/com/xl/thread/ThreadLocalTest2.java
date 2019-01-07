package com.xl.thread;

import lombok.extern.java.Log;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-12-22
 * @Time: 19:47
 * To change this template use File | Settings | File Templates.
 */
@Log
public class ThreadLocalTest2 {
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>() {
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };
    ThreadLocal<String> stringLocal = new ThreadLocal<String>() {
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };
    
    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }
    
    public long getLong() {
        return longLocal.get();
    }
    
    public String getString() {
        return stringLocal.get();
    }
    
    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest2 test = new ThreadLocalTest2();
        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());
        Thread thread1 = new Thread() {
            public void run() {
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            }
            
            ;
        };
        thread1.start();
        thread1.join();
        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
