package com.xl.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.不能保证原子性
 * volatile最适用一个线程写，多个线程读的场合。
 * <p>
 * 如果有多个线程并发写操作，仍然需要使用锁或者线程安全的容器或者原子变量来代替。(摘自Netty权威指南)
 * <p>
 * 疑问：如果只是赋值的原子操作，是否可以多个线程写？(答案：可以，但是一般没有这样的必要，即没有这样的应用场景)
 * 结合使用 volatile 和 synchronized 实现 “开销较低的读－写锁”
 * <p>
 * volatile 允许多个线程执行读操作，因此当使用 volatile 保证读代码路径时，要比使用锁执行全部代码路径获得更高的共享度 —— 就像读－写操作一样。
 * 您只能在有限的一些情形下使用 volatile 变量替代锁。要使 volatile 变量提供理想的线程安全，必须同时满足下面两个条件：
 * <p>
 * 对变量的写操作不依赖于当前值。
 * 该变量没有包含在具有其他变量的不变式中。
 * 更多使用场景可参考：
 * volatile不需要加锁，比synchronized更轻量级，不会阻塞线程。
 * 从内存可见性角 volatile读相当于枷锁，volatile写相当于解锁。
 * synchronize能保证可见性‘又能保证原子性，volatile无法保证原子性
 *
 * @author: 徐立
 * @Date: 2018-04-30
 * @Time: 21:51
 * To change this template use File | Settings | File Templates.
 */
public class VolatileDemo {
    private Lock lock = new ReentrantLock();
    private int number = 0;

    public int getNumber() {
        return this.number;
    }

    public void increase() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        try {
            this.number++;
        } finally {
            lock.unlock();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        final VolatileDemo volDemo = new VolatileDemo();
        for (int i = 0; i < 500; i++) {
            new Thread(() -> volDemo.increase()).start();
        }
        //如果还有子线程在运行，主线程就让出CPU资源，
        //直到所有的子线程都运行完了，主线程再继续往下执行
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println("number : " + volDemo.getNumber());
    }
}
