package com.xl;

import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.GC回收钱会触发finalize方法
 *
 * @author 徐立
 * @date 2019-11-30
 * @time 22:06
 * To change this template use File | Settings | File Templates.
 */
public class FinalizeTest {
    @Test
    void name() throws InterruptedException {
        demo1();
        //finalize方法没有执行
        Thread.sleep(3000);
        demo1();
    }
    
    private void demo1() {
        FinalizeTest finalizeTest = new FinalizeTest();
        finalizeTest = null;
        System.gc();
    }
    
    @Override
    protected void finalize() {
        System.out.println("这是finalize方法");
    }
}
