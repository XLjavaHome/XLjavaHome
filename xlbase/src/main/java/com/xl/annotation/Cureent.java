package com.xl.annotation;

import java.util.concurrent.CyclicBarrier;
import org.testng.annotations.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-04
 * @time 20:24
 * To change this template use File | Settings | File Templates.
 */
public class Cureent {
    @Test
    public void name() {
        //多线程并发安全
        CyclicBarrier cb = new CyclicBarrier(20);
    }
}
