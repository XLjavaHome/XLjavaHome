package com.xl.thread;

import com.xl.util.StringUtil;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomUtils;

/**
 * Created with 徐立.线程池的测试
 *
 * @author: 徐立
 * @Date: 2019-01-05
 * @Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
public class ThreadPoolExecutorReturnTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService poolExecutor = new ThreadPoolExecutor(5, 10, 300, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
        Callable<String> run = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName() + ":" + RandomUtils.nextInt(1, 100);
        };
        Set<String> result = new HashSet<>(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Future<String> submit = poolExecutor.submit(run);
            result.add(submit.get());
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        //这里一定要做关闭
        poolExecutor.shutdown();
        System.out.println(StringUtil.join(result, "\n"));
    }
}
