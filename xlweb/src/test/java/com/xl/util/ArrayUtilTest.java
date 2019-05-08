package com.xl.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-06-20
 * @Time: 16:47
 * To change this template use File | Settings | File Templates.
 */
public class ArrayUtilTest {
    @Test
    public void containsTest() throws Exception {
        //Integer也包含
        Integer[] arr = new Integer[]{1, 163};
        Integer i = 163;
        System.out.println(ArrayUtils.contains(arr, i));
    }

    @Test
    public void arrayReduceLength() throws Exception {
    }

    @Test
    public void getMin() throws Exception {
    }

    @Test
    public void swap() throws Exception {
    }

    @Test
    public void delete() throws Exception {
        List list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        System.out.println(list.size());
        ArrayUtil.delete(list, 1000);
        System.out.println(list.size());
    }
}
