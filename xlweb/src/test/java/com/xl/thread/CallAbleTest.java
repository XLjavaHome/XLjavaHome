package com.xl.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2019-01-11
 * @Time: 9:59
 * To change this template use File | Settings | File Templates.
 */
public class CallAbleTest {
    public static void main(String[] args) {
        Callable<String> a = Executors.callable(new Runnable() {
            @Override
            public void run() {
                System.out.println("111");
            }
        }, "测试");
        System.out.println(a);
    }
}
