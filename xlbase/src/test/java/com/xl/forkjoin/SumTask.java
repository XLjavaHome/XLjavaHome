package com.xl.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-23
 * @time 22:44
 * To change this template use File | Settings | File Templates.
 */
public class SumTask extends RecursiveTask<Integer> {
    /**
     * 最小的分割点
     */
    private int threshold = 5;
    private int start;
    private int end;
    
    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //获取当前系统CPU核数
        int coreNumber = Runtime.getRuntime().availableProcessors();
        ForkJoinPool forkJoinPool = new ForkJoinPool(coreNumber);
        SumTask task = new SumTask(1, 100);
        ForkJoinTask<Integer> result = forkJoinPool.submit(task);
        System.out.println(result.get());
    }
    
    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            //具体的逻辑
            sum = specificLogic(sum);
        } else {
            //进行拆分
            sum = myFork();
        }
        return sum;
    }
    
    /**
     * 具体的逻辑
     *
     * @param sum
     * @return
     */
    private int specificLogic(int sum) {
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
    
    private int myFork() {
        int sum;
        int middle = (start + end) / 2;
        SumTask leftTask = new SumTask(start, middle);
        SumTask rightTask = new SumTask(middle + 1, end);
        //任务分割
        leftTask.fork();
        rightTask.fork();
        //等待子任务结束得到结果
        int leftResult = leftTask.join();
        int rightResult = rightTask.join();
        //合并
        sum = leftResult + rightResult;
        return sum;
    }
}
