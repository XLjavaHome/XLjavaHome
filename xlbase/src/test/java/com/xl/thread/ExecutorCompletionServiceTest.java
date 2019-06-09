package com.xl.thread;

import java.util.concurrent.*;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-07
 * @time 14:24
 * To change this template use File | Settings | File Templates.
 */
public class ExecutorCompletionServiceTest {
    /**
     * 线程池get方法是阻塞的，先获取就用ExecutorCompletionService
     * ExecutorCompletionService实现了CompletionService接口，在CompletionService接口中定义了如下这些方法：
     * <p>
     * Future<V> submit(Callable<V> task):提交一个Callable类型任务，并返回该任务执行结果关联的Future；
     * <p>
     * Future<V> submit(Runnable task,V result):提交一个Runnable类型任务，并返回该任务执行结果关联的Future；
     * <p>
     * Future<V> take():从内部阻塞队列中获取并移除第一个执行完成的任务，阻塞，直到有任务完成；
     * <p>
     * Future<V> poll():从内部阻塞队列中获取并移除第一个执行完成的任务，获取不到则返回null，不阻塞；
     * <p>
     * Future<V> poll(long timeout, TimeUnit unit):从内部阻塞队列中获取并移除第一个执行完成的任务，阻塞时间为timeout，获取不到则返回null；
     *
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws ExecutionException
     */
    @Test
    public void test() throws InterruptedException, ExecutionException, ExecutionException {
        Executor executor = Executors.newFixedThreadPool(3);
        CompletionService<String> service = new ExecutorCompletionService(executor);
        for (int i = 0; i < 5; i++) {
            int seqNo = i;
            service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "HelloWorld-" + seqNo + "-" + Thread.currentThread().getName();
                }
            });
        }
        for (int j = 0; j < 7; j++) {
            //内部阻塞队列中获取并移除第一个执行完成的任务，阻塞，直到有任务完成；
            System.out.println(service.take().get());
        }
        System.out.println("执行完毕");
    }
}
