package com.xl.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-12-08
 * @Time: 19:37 To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
@Log4j
public class ThreadReturnValue implements Callable<Double> {
    private Long time;
    
    @Override
    public Double call() throws Exception {
        Thread.sleep(time);
        double result = new Random().nextDouble();
        log.info(Thread.currentThread().getName() + ":" + result);
        return result;
    }
}
