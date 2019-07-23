package com.xl;

import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-23
 * @time 16:52
 * To change this template use File | Settings | File Templates.
 */
public class ForkJoinPoolTest {
    @Test
    void name() {
        //创建 类静态代码块中会调用makeCommonPool方法初始化一个commonPool
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        // 3ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());
    }
}

