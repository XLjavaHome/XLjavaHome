package com.xl.base;

import java.util.ArrayList;
import org.junit.Test;

/**
 * Created with 徐立.可以代替JDK自带的类
 *
 * @author 徐立
 * @date 2019-06-10
 * @time 9:13
 * To change this template use File | Settings | File Templates.
 */
public class EndorsedTest {
    public EndorsedTest() {
    }
    
    @Test
    public void name() {
        ArrayList<String> list = new ArrayList<String>(10);
        for (int i = 0; i < 10; i++) {
            list.add("test" + i);
            list.get(i);
        }
    }
}
