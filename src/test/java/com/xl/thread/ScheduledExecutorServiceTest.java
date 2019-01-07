package com.xl.thread;

import static java.lang.System.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2018-10-27
 * @Time: 12:18
 * To change this template use File | Settings | File Templates.
 */
public class ScheduledExecutorServiceTest {
    public static void main(String[] args) {
        //延迟3秒，每隔2秒执行
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> out.println("run " + currentTimeMillis()), 3000, 2000, TimeUnit.MILLISECONDS);
        //后面的参数代表前面的参数
        //executorService.scheduleAtFixedRate(() -> out.println("run " + currentTimeMillis()), 2, 1, TimeUnit.SECONDS);
        //只执行一次
        executorService.schedule(() -> {
            out.println("run " + currentTimeMillis());
        }, 1, TimeUnit.SECONDS);
    }
}                    
