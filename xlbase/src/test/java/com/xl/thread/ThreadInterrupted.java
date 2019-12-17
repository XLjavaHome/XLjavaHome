package com.xl.thread;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-12-16
 * @time 23:55
 * To change this template use File | Settings | File Templates.
 */
public class ThreadInterrupted {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.interrupted();
                if (interrupted) {
                    System.out.println("测试");
                }
            }
        });
        thread1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //直接中断没有效果
        thread1.interrupt();
    }
}
