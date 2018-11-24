package com.xl.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created with 徐立Callable实现有返回值多线程.
 *
 * @author: 徐立
 * @Date: 2018-11-24
 * @Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
public class ThreadCallableTest implements Callable<String> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
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
