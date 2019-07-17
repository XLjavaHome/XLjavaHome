package com.xl;

import java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-16
 * @time 21:45
 * To change this template use File | Settings | File Templates.
 */
public class XLTest {
    @Test
    void demo() {
        System.out.println(Arrays.toString(StringUtils.split(",1,3,4,,4", ',')));
    }
    
    @Test
    void name() {
        System.out.println("111");
    }
}
