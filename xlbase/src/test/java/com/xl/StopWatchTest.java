package com.xl;

import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-10-18
 * @time 13:04
 * To change this template use File | Settings | File Templates.
 */
public class StopWatchTest {
    @Test
    void name() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(3000);
        //要调用stop才可以
        stopWatch.stop();
        //这打印这种打印最好
        System.out.println(stopWatch);
        System.out.println(stopWatch.shortSummary());
        System.out.println(stopWatch.getTotalTimeMillis());
        System.out.println(stopWatch.getTotalTimeSeconds());
    }
}
