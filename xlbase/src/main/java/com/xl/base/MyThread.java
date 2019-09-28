package com.xl.base;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-28
 * @time 10:56
 * To change this template use File | Settings | File Templates.
 */
public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }
    
    public MyThread(ThreadGroup tg, String name) {
        super(tg, name);
    }
}
