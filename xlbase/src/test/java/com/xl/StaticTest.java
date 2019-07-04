package com.xl;

import lombok.extern.log4j.Log4j;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-04
 * @time 20:36
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class StaticTest {
    /**
     * volatile 工作内存变量可见性 汇编的lock操作 缓存行锁
     */
    public static volatile boolean flag = false;
    
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            log.info("数据准备");
            while (!flag) {
            }
            log.info("准备完毕");
        }).start();
        Thread.sleep(2000);
        new Thread(() -> {
            String 数据准备 = "数据准备";
            log.info(数据准备);
            flag = true;
            log.info("准备完毕");
        }).start();
    }
}
