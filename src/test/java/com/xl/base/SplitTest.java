package com.xl.base;

import java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2019-02-11
 * @Time: 13:16
 * To change this template use File | Settings | File Templates.
 */
public class SplitTest {
    /**
     * 字符串分割
     */
    @Test
    public void splitTest() {
        //可以传null
        System.out.println(Arrays.toString(StringUtils.split(null, ',')));
        System.out.println(Arrays.toString(StringUtils.split("1,2,3,4", ',')));
    }
}
