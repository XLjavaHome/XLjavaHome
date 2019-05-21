package com.xl.util.excel;

import java.util.Hashtable;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-21
 * @time 17:07
 * To change this template use File | Settings | File Templates.
 */
public class HashtableTest {
    public static void main(String[] args) throws InterruptedException {
        Hashtable hashtable = new Hashtable();
        int max = 10000;
        Thread[] threads = new Thread[max];
        for (int i = 0; i < max; i++) {
            int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    hashtable.put(finalI, finalI);
                }
            });
            thread.start();
            threads[i] = thread;
        }
        System.out.println(hashtable.size());
        for (int i = 0; i < max; i++) {
            threads[i].join();
        }
        System.out.println(hashtable.size());
    }
}
