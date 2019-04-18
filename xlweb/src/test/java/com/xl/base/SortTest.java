package com.xl.base;

import com.xl.comparator.IntegerComparator;
import com.xl.enumsupport.Sort;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Set;
import org.junit.Test;

/**
 * Created with 徐立. 排序
 *
 * @author: 徐立
 * @Date: 2018-10-28
 * @Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
public class SortTest {
    /**
     * 堆排序,add就是off，迭代（iterator）是无法排序，获取队列的头（poll）才能排序。
     */
    @Test
    public void priorityQueueTest() {
        PriorityQueue priorityQueue = new PriorityQueue(new IntegerComparator(Sort.ASC));
        sortTest(priorityQueue);
        PriorityQueue priorityQueue2 = new PriorityQueue(new IntegerComparator(Sort.DESC));
        sortTest(priorityQueue2);
    }
    
    private void sortTest(PriorityQueue priorityQueue) {
        Set<Integer> set = new LinkedHashSet<>();
        priorityQueue.offer(6);
        priorityQueue.offer(4);
        priorityQueue.offer(7);
        priorityQueue.offer(-3);
        priorityQueue.offer(1);
        System.out.println(Arrays.toString(priorityQueue.toArray()));
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
    }
}
