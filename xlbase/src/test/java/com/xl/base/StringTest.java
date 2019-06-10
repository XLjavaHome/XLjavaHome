package com.xl.base;

import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-10
 * @time 15:42
 * To change this template use File | Settings | File Templates.
 */
public class StringTest {
    @Test
    void replace() {
        String str = "你好a你as好adewn你好o323";
        System.out.println(str.replaceAll("[你好]", ""));
        System.out.println(str.replace("你好", ""));
    }
}
