package com.xl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-21
 * @time 13:47
 * To change this template use File | Settings | File Templates.
 */
public class JConsoleTest {
    public static void main(String[] args) throws InterruptedException {
        bigOOM();
    }
    
    public static void bigOOM() throws InterruptedException {
        List<OoMObiect> list = new ArrayList<>();
        int num = 1000;
        for (int i = 0; i < num; i++) {
            Thread.sleep(200);
            list.add(new OoMObiect());
        }
        System.gc();
    }
    
    static class OoMObiect {
        public byte[] placeHold = new byte[64 * 1024];
    }
}
