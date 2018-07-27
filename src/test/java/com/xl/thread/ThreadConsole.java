package com.xl.thread;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-07-27
 * @Time: 10:10
 * To change this template use File | Settings | File Templates.
 */
public class ThreadConsole {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //空转
                }
            }
        }, "while thread").start();
        scanner.next();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Object object = new Object();
                synchronized (object) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
