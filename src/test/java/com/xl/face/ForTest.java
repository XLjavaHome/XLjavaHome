package com.xl.face;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.System.currentTimeMillis1970年1月1号0时0分0秒所差的毫秒数。<br/>
 * java中获取微秒不准确
 * s（秒）、ms（毫秒）、μs（微秒）、ns（纳秒），
 * 其中：1s=1000ms，1 ms=1000μs，1μs=1000ns
 *
 * @author: 徐立
 * @Date: 2018-05-02
 * @Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
public class ForTest {
    /**
     * 1万次循环做判断是0毫秒
     * 10万次循环做判断是2毫秒
     */
    @Test
    public void forTimeTest() {
        long last = System.currentTimeMillis();
        int num = 100000;
        for (int i = 1; i < num; i++) {
            if (1 > 5000) {
                boolean flag = true;
            } else {
                boolean flag = false;
            }
        }
        long now = System.currentTimeMillis();
        System.out.println(now - last);
    }
}

