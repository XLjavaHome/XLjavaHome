package com.xl.base.design.producerandconsumer;

import com.xl.entity.PCData;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with 徐立. 生产者
 *
 * @author 徐立
 * @Date: 2018-12-04
 * @Time: 23:08
 * To change this template use File | Settings | File Templates.
 */
public class Producer implements Runnable {
    private volatile boolean isRunning = true;
    private BlockingQueue<PCData> queue;// 内存缓冲区
    private static AtomicInteger count = new AtomicInteger();// 总数 原子操作
    private static final int SLEEPTIME = 1000;
    
    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        PCData data = null;
        Random r = new Random();
        System.out.println("start producting id:" + Thread.currentThread().getId());
        try {
            while (isRunning) {
                Thread.sleep(r.nextInt(SLEEPTIME));
                data = new PCData(count.incrementAndGet());
                System.out.println(data + " 加入队列");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.err.println(" 加入队列失败");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    
    public void stop() {
        isRunning = false;
    }
}
