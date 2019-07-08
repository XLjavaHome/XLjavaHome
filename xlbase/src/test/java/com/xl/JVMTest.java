package com.xl;

import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-05
 * @time 22:49
 * To change this template use File | Settings | File Templates.
 */
public class JVMTest {
    public static final Integer constant = 666;
    
    /**
     * 局部变量存放于虚拟机栈
     *
     * @return
     */
    public int add() {
        int a = 1;
        int b = 2;
        int c = a + b * 10;
        add();
        return c;
    }
    
    @Test
    void name() {
        JVMTest jvmTest = new JVMTest();
        jvmTest.add();
    }
}
