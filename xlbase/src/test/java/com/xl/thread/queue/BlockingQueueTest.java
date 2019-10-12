package com.xl.thread.queue;

import com.xl.base.design.producerandconsumer.Consumer;
import com.xl.base.design.producerandconsumer.Producer;
import static java.lang.Thread.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-08
 * @time 17:36
 * To change this template use File | Settings | File Templates.
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(5);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
        sleep(4000);
    }
}


