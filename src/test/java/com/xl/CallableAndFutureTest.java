package com.xl;

import com.xl.util.NumberTool;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang.math.RandomUtils;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-11-08
 * @Time: 9:39
 * To change this template use File | Settings | File Templates.
 */
public class CallableAndFutureTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        //创建一个线程池，该线程池重用固定数量的从共享无界队列中运行的线程。
        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
        for (int i = 1; i <= 15; i++) {
            completionService.submit(() -> {
                try {
                    //放在里面会耗时很少
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int shuzi = RandomUtils.nextInt(100);
                return shuzi;
            });
        }
        Integer[] doubles = new Integer[10];
        for (int i = 0; i < 10; i++) {
            try {
                doubles[i] = completionService.take().get();
                System.out.print(doubles[i] + ",");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        double sum = NumberTool.safeToAdd(doubles);
        System.out.println("\n" + sum);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
