package com.xl.face;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created with IntelliJ IDEA.在一个多线程程序中，如果需要生成随机数，应该使用 java se7 中新增的 java.util.concurrent.ThreadLocalRandom 类 ，避免了 使用 java.util.Random 对象可能带来的竞争问题，可以获得更佳的性能。
 *
 * @author: 徐立
 * @Date:2018-04-18
 * @Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
public class RandomTest {
    private static Random rnd = new Random();

    @Test
    public void demoTest() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        System.out.println(current.nextInt(10));
        System.out.println(current.nextInt(10));
        System.out.println(current.nextInt(10));
        System.out.println(current.nextInt(10));
    }
}
