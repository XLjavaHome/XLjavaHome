package com.xl.thread;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

/**
 * Created with 徐立.原子自增
 *
 * @author 徐立
 * @date 2019-03-18
 * @time 12:58
 * To change this template use File | Settings | File Templates.
 */
public class AtomicIntegerTest {
    @Test
    public void 自增() throws InterruptedException {
        java.util.concurrent.atomic.AtomicInteger atomicInteger = new AtomicInteger();
        for (int i = 0; i < 1000; i++) {
            Thread thread = add(atomicInteger);
            thread.join();
        }
        System.out.println(atomicInteger.get());
    }
    
    private Thread add(AtomicInteger atomicInteger) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInteger.getAndIncrement();
            }
        });
        thread.start();
        return thread;
    }
}
