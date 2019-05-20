package com.xl.util;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-20
 * @time 13:39
 * To change this template use File | Settings | File Templates.
 */
public class ArrayUtilTest {
    @Test
    public void 包含测试() {
        Integer[] arr = new Integer[]{2, 1111, 3232};
        System.out.println(ArrayUtils.contains(arr, 1111));
        System.out.println(ArrayUtils.contains(arr, 222));
    }
}
