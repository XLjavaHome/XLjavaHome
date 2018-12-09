package com.xl.thread;

import com.xl.entity.ThreadReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j;

/**
 * Created with 徐立Callable实现有返回值多线程.
 *
 * @author: 徐立
 * @Date: 2018-11-24
 * @Time: 17:05 To change this template use File | Settings | File Templates.
 */
@Log4j
public class ThreadCallableTest implements Callable<String> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //demo1();
        ExecutorService pool = Executors.newFixedThreadPool(3);
        Future<Double> future = pool.submit(new ThreadReturnValue(3000L));
        Future<Double> future2 = pool.submit(new ThreadReturnValue(2000L));
        Future<Double> future3 = pool.submit(new ThreadReturnValue(1000L));
        pool.submit(() -> log.info("测试"));
        
        // 不允许再向线程池中增加线程
        pool.shutdown();
        //判断是否所有线程已经执行完毕
        try {
            boolean isFinish = pool.awaitTermination(1, TimeUnit.HOURS);
            System.out.println(isFinish + "==========================");
            //如果没有执行完
            if (!isFinish) {
                //线程池执行结束 不在等待线程执行完毕，直接执行下面的代码
                log.info("线程没有结束");
                pool.shutdownNow();
            }
            Double r = future.get();
            Double r2 = future2.get();
            Double r3 = future3.get();
            log.info("第一个线程：" + r);
            log.info("第2个线程：" + r2);
            log.info("第3个线程：" + r3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //只给线程池中的线程1小时，然后就继续执行
        System.out.println("it is ok !!!");
    }
    
    private static void demo1() throws InterruptedException, ExecutionException {
        FutureTask task = new FutureTask(new ThreadCallableTest());
        new Thread(task).start();
        //拿到结果
        System.out.println(task.get());
    }
    
    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            sb.append(i);
        }
        return sb.toString();
    }
}
