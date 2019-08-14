package com.xl.thread.queue;

import java.util.PriorityQueue;
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
}
