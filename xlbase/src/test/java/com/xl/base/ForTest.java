package com.xl.base;

import java.util.stream.IntStream;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-30
 * @time 9:19
 * To change this template use File | Settings | File Templates.
 */
public class ForTest {
    @Test
    public void forTest() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
        }
    }
    
    @Test
    public void stream() {
        IntStream.range(0, 10000).forEach(System.out::println);
    }
    
    @Test
    public void 添加逗号分割() {
        int max = 10;
        StringBuffer sql = new StringBuffer();
        for (int i = 0; i < max; i++) {
            sql.append(i);
            //要减1
            if (i < max - 1) {
                sql.append(",");
            }
        }
        System.out.println(sql);
    }
}
