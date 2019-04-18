package com.xl.thread;

import lombok.extern.log4j.Log4j;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2019-01-25
 * @Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class ThreadLogTest {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("1");
            log.info("1");
        }).start();
        new Thread(() -> {
            System.out.println("2");
            log.info("2");
        }).start();
        new Thread(() -> {
            System.out.println("3");
            log.info("3");
        }).start();
    }
}
