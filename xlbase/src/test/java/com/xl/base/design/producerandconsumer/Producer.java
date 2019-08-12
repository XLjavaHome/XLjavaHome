package com.xl.base.design.producerandconsumer;

import com.xl.entity.PCData;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

/**
 * Created with 徐立. 生产者
 *
 * @author 徐立
 * @Date: 2018-12-04
 * @Time: 23:08
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
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
        PCData data;
        Random r = new Random();
        String x = "start producting id:" + Thread.currentThread().getId();
        log.info(x);
        try {
            while (isRunning) {
                Thread.sleep(r.nextInt(SLEEPTIME));
                data = new PCData(count.incrementAndGet());
                log.info(data + " 加入队列");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    String s = " 加入队列失败";
                    log.info(s);
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
