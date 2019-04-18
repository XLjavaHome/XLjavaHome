package com.xl.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author 徐立
 * @Decription 要实现共享静态数据的效果，在线程内共享，在线程外独立
 * @date 2014年3月5日
 */
public class ThreadShareData {
    private static int data = 0;
    private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();

    public static void main(String[] args) {
        for (int i = 1; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data = new Random().nextInt();
                    threadData.put(Thread.currentThread(), data);
                    System.out.println(Thread.currentThread().getName() + ":" + data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        public void get() {
            System.out.println("A from" + Thread.currentThread().getName() + ":" + data);
        }
    }

    static class B {
        public void get() {
            System.out.println("B from" + Thread.currentThread().getName() + ":" + data);
        }
    }
}
