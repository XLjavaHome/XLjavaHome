package com.xl.face;

import org.junit.Test;

/**
 * @author 徐立
 * @Decription 整数边界的问题:
 * @date 2014-5-15
 */
public class WhileTest {
    public static final int END = Integer.MAX_VALUE;
    public static final int START = END - 100;

    @Test
    public void test1() {
        int count = 0;
        for (int i = START; i <= END; i++) {
            count++;
            System.out.println(i);
        }
        System.out.println(count);
    }
}
