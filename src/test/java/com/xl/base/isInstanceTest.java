package com.xl.base;

import java.math.BigDecimal;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-02-27
 * @time 14:09
 * To change this template use File | Settings | File Templates.
 */
public class isInstanceTest {
    /**
     * 看看是否能为空
     */
    @Test
    public void isInstanceTest() {
        System.out.println(Number.class.isInstance(1));
        //null就是false
        System.out.println(Number.class.isInstance(null));
        //false
        System.out.println(BigDecimal.class.isInstance(null));
        //false
        System.out.println(BigDecimal.class.isInstance(1));
        //true
        System.out.println(BigDecimal.class.isInstance(new BigDecimal(1)));
    }
}
