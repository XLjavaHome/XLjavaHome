package com.xl.thread.queue;

import java.util.PriorityQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-14
 * @time 18:07
 * To change this template use File | Settings | File Templates.
 */
public class QueueTest {
    @Test
    void name() {
        PriorityQueue<Integer> queue = new PriorityQueue();
        queue.add(3);
        queue.add(5);
        queue.add(2);
        queue.add(4);
        System.out.println(queue.peek());
    }
    
    /**
     * poll：将首个元素从队列中弹出，如果队列是空的，就返回null
     * peek：查看首个元素，不会移除首个元素，如果队列是空的就返回null
     * element：查看首个元素，不会移除首个元素，如果队列是空的就抛出异常NoSuchElementException
     */
    @Test
    void 无界LinkedBlockingQueue() {
        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue();
        for (int i = 6; i < 10; i++) {
            linkedBlockingQueue.add(i);
        }
        //poll出列
        Integer poll = linkedBlockingQueue.poll();
        System.out.println(poll);
        Integer peek = linkedBlockingQueue.peek();
        System.out.println(peek);
        Integer element = linkedBlockingQueue.element();
        System.out.println(element);
    }
}
