package com.xl;

import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-12-11
 * @time 22:45
 * To change this template use File | Settings | File Templates.
 */
public class StringTest {
    @Test
    void codeTest() {
        String a = "测试";
        System.out.println(Objects.hash(a));
        System.out.println(a.toString());
    }
    
    @Test
    void joinTest() {
        System.out.println(String.join("or", "1", "2"));
    }
}
