package com.xl.base.design.producerandconsumer;

import com.xl.entity.PCData;
import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import lombok.extern.slf4j.Slf4j;

/**
 * Created with 徐立. 消费者
 *
 * @author 徐立
 * @Date: 2018-12-04
 * @Time: 23:15
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class Consumer implements Runnable {
    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME = 1000;
    
    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        String x = "start Consumer id :" + Thread.currentThread().getId();
        log.info(x);
        Random r = new Random();
        try {
            while (true) {
                PCData data = queue.take();
                if (data != null) {
                    int re = data.getData() * data.getData();
                    log.info(MessageFormat.format("{0}*{1}={2}", data.getData(), data.getData(), re));
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
