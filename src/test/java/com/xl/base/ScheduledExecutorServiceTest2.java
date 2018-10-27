package com.xl.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-10-27
 * @Time: 12:18
 * To change this template use File | Settings | File Templates.
 */
public class ScheduledExecutorServiceTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Callable> callableList = new ArrayList<>();
        callableList.add(new MyCallableA());
        callableList.add(new MyCallableB());
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture futureA = executorService.schedule(callableList.get(0), 4L, TimeUnit.SECONDS);
        ScheduledFuture futureB = executorService.schedule(callableList.get(1), 4L, TimeUnit.SECONDS);
        System.out.println("            X = " + System.currentTimeMillis());
        System.out.println("返回值A：" + futureA.get());
        System.out.println("返回值B：" + futureB.get());
        System.out.println("            Y = " + System.currentTimeMillis());
        //结束
        //executorService.shutdown();
    }

    static class MyCallableA implements Callable<String> {
        @Override
        public String call() throws Exception {
            try {
                System.out.println("callA begin " + Thread.currentThread().getName() + ", " + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3); // 休眠3秒
                System.out.println("callA end " + Thread.currentThread().getName() + ", " + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "returnA";
        }
    }

    static class MyCallableB implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("callB begin " + Thread.currentThread().getName() + ", " + System.currentTimeMillis());
            System.out.println("callB end " + Thread.currentThread().getName() + ", " + System.currentTimeMillis());
            return "returnB";
        }
    }
}
