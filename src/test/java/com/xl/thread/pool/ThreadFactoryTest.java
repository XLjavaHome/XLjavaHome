package com.xl.thread.pool;

import avro.shaded.com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.math.RandomUtils;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2019-01-11
 * @Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class ThreadFactoryTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(() -> System.out.println(Thread.currentThread().getName()));
        Future<Double> one = threadPoolExecutor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                double result = RandomUtils.nextDouble();
                System.out.println(Thread.currentThread().getName());
                log.info(result);
                Thread.sleep(5000);
                return result;
            }
        });
        Future<Double> two = threadPoolExecutor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                double result = RandomUtils.nextDouble();
                System.out.println(Thread.currentThread().getName());
                log.info(result);
                return result;
            }
        });
        threadPoolExecutor.shutdown();
        Double oneDouble = one.get();
        Double twoDouble = two.get();
        System.out.println(oneDouble);
        System.out.println(twoDouble);
    }
}
