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
     * String.split()会包含空字符串,而且是包含 头部的和中间的, 不包含有效数字后面所有的空字符串.
     * StringUtils.split()会过滤所有的空字符串. 当然空格不会被过滤.
     */
    @Test
    public void splitTest() {
        //可以传null
        System.out.println(Arrays.toString(StringUtils.split(null, ',')));
        System.out.println(Arrays.toString(StringUtils.split("1,2,3,4", ',')));
    }
}
