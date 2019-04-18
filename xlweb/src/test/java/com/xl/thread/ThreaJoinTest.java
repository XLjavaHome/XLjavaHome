package com.xl.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with 徐立. 等待线程执行完毕
 *
 * @author: 徐立
 * @Date: 2018-11-24
 * @Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
public class ThreaJoinTest implements Runnable {
    private static List result = new ArrayList();
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreaJoinTest());
        thread.start();
        Thread.sleep(100);
        System.out.println(result.size());
        System.out.println(result.size());
        //等待线程执行完毕
        thread.join();
        System.out.println(result.size());
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++) {
            result.add(i);
        }
    }
}
