package com.xl.base;

/**
 * Created with 徐立.  java不推荐使用线程组
 * 1.
 * 线程组stop resume suspend 会导致线程安全问题（主要是死锁）
 * 2.
 * 线程组ThreadGroup 不是线程安全
 *
 * @author 徐立
 * @date 2019-09-28
 * @time 10:54
 * To change this template use File | Settings | File Templates.
 */
public class ThreadGroupTest {
    public static void main(String[] args) {
        // 获取主线程所在的线程组，这是所有线程默认的线程组
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组的名字：" + mainGroup.getName());
        System.out.println("主线程组是否是守护线程组：" + mainGroup.isDaemon());
        new MyThread("主线程组的线程").start();
        ThreadGroup tg = new ThreadGroup("新线程组");
        tg.setDaemon(true);
        System.out.println("tg线程组是否是守护线程组：" + tg.isDaemon());
        MyThread tt = new MyThread(tg, "tg组的线程甲");
        tt.start();
        new MyThread(tg, "tg组的线程乙").start();
    }
}
