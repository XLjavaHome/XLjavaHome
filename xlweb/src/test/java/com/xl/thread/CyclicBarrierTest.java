package com.xl.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created with IntelliJ IDEA. CyclicBarrier允许一组线程互相等待，直到到达某个公共屏障点。叫做cyclic是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用(对比于CountDownLatch是不能重用的)
 * 使用说明：
 * <p>
 * CountDownLatch注重的是等待其他线程完成，CyclicBarrier注重的是：当线程到达某个状态后，暂停下来等待其他线程，所有线程均到达以后，继续执行。
 * <p>
 *
 * @author: 徐立
 * @Date: 2018-07-27
 * @Time: 12:59
 * To change this template use File | Settings | File Templates.
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        final CyclicBarrier CyclicBarrier = new CyclicBarrier(2);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                if (name.equals("Thread-0")) {
                    name = "3y";
                } else {
                    name = "女朋友";
                }
                System.out.println(name + "到了体育西");
                try {
                    // 两个人都要到体育西才能发朋友圈
                    CyclicBarrier.await();
                    // 他俩到达了体育西，看见了对方发了一条朋友圈：
                    System.out.println("跟" + name + "去夜上海吃东西~");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
